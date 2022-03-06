package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import com.jsblock.packets.Server;
import mtr.MTR;
import mtr.block.IBlock;
import mtr.mappings.BlockEntityClientSerializableMapper;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.EntityBlockMapper;
import mtr.mappings.TickableMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundCustomSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SoundLooper extends Block implements EntityBlockMapper {

	public SoundLooper(Properties settings) {
		super(settings);
	}

	@Override
	public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntitySoundLooper(pos, state);
	}

	@Override
	public <T extends BlockEntityMapper> void tick(Level world, BlockPos pos, T blockEntity) {
		SoundLooper.TileEntitySoundLooper.tick(world, pos, blockEntity);
	}

	@Override
	public BlockEntityType<? extends BlockEntityMapper> getType() {
		return BlockEntityTypes.SOUND_LOOPER_TILE_ENTITY;
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		return IBlock.checkHoldingBrush(world, player, () -> {
			final BlockEntity entity = world.getBlockEntity(pos);

			if (entity instanceof TileEntitySoundLooper) {
				Server.openSoundLooperScreenS2C((ServerPlayer) player, pos);
			}
		});
	}

	public static class TileEntitySoundLooper extends BlockEntityClientSerializableMapper implements TickableMapper {

		private static String soundID = "";
		private static int repeatTick = 20;
		private static float soundVolume = 1;
		private static int soundCategory = 0;
		private static boolean requireRedstone = false;
		private static final String KEY_REPEAT_TICK = "repeat_tick";
		private static final String KEY_SOUND_ID = "sound_id";
		private static final String KEY_SOUND_CATEGORY = "sound_category";
		private static final String KEY_NEED_REDSTONE = "need_redstone";
		private static final String KEY_SOUND_VOLUME = "volume";
		private static final SoundSource[] SOURCE_LIST = {SoundSource.MASTER, SoundSource.MUSIC, SoundSource.WEATHER, SoundSource.AMBIENT, SoundSource.PLAYERS};
		public TileEntitySoundLooper(BlockPos pos, BlockState state) {
			super(BlockEntityTypes.SOUND_LOOPER_TILE_ENTITY, pos, state);
		}

		@Override
		public void readCompoundTag(CompoundTag compoundTag) {
			repeatTick = compoundTag.getInt(KEY_REPEAT_TICK);
			soundID = compoundTag.getString(KEY_SOUND_ID);
			soundCategory = compoundTag.getInt(KEY_SOUND_CATEGORY);
			soundVolume = compoundTag.getFloat(KEY_SOUND_VOLUME);
			requireRedstone = compoundTag.getBoolean(KEY_NEED_REDSTONE);
		}

		@Override
		public void writeCompoundTag(CompoundTag compoundTag) {
			compoundTag.putInt(KEY_REPEAT_TICK, repeatTick);
			compoundTag.putString(KEY_SOUND_ID, soundID);
			compoundTag.putInt(KEY_SOUND_CATEGORY, soundCategory);
			compoundTag.putFloat(KEY_SOUND_VOLUME, soundVolume);
			compoundTag.putBoolean(KEY_NEED_REDSTONE, requireRedstone);
		}

		@Override
		public void tick() {
			tick(level, worldPosition, this);
		}

		public static <T extends BlockEntityClientSerializableMapper> void tick(Level level, BlockPos pos, BlockEntity entity) {
			if (repeatTick > 0 && MTR.isGameTickInterval(repeatTick) && !soundID.isEmpty()) {

				if(level == null || level.isClientSide()) return;
				if (requireRedstone && !level.hasNeighborSignal(pos)) return;

				level.players().forEach(player -> {
					try {
						final ResourceLocation soundLocation = new ResourceLocation(soundID);
						final SoundSource source = SOURCE_LIST[soundCategory];
						((ServerPlayer)player).connection.send(new ClientboundCustomSoundPacket(soundLocation, source, new Vec3(pos.getX(), pos.getY(), pos.getZ()), soundVolume, 1));
					} catch (Exception ignored) {
					}
				});
			}
		}

		public String getSoundId() {
			return soundID == null ? "" : soundID;
		}

		public int getLoopInterval() {
			return repeatTick;
		}
		public int getSoundCategory() {
			if(soundCategory > SOURCE_LIST.length) {
				soundCategory = 0;
			}
			return soundCategory;
		}

		public void setData(String soundId, int soundCategory, int interval, float volume, boolean requireRedstone) {
			TileEntitySoundLooper.soundID = soundId;
			TileEntitySoundLooper.repeatTick = interval;
			TileEntitySoundLooper.soundCategory = soundCategory;
			TileEntitySoundLooper.soundVolume = volume;
			TileEntitySoundLooper.requireRedstone = requireRedstone;
			syncData();
		}

		public float getSoundVolume() {
			return soundVolume;
		}

		public boolean getNeedRedstone() {
			return requireRedstone;
		}
	}
}