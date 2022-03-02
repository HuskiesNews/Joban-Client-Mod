package com.jsblock.blocks;

import com.jsblock.BlockEntityTypes;
import com.jsblock.packets.Server;
import mtr.MTR;
import mtr.block.IBlock;
import mtr.mappings.BlockEntityClientSerializableMapper;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.EntityBlockMapper;
import mtr.mappings.TickableMapper;
import net.minecraft.ResourceLocationException;
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
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		return IBlock.checkHoldingBrush(world, player, () -> {
			final BlockEntity entity = world.getBlockEntity(pos);

			if (entity instanceof TileEntitySoundLooper) {
				Server.openSoundLooperScreenS2C((ServerPlayer) player, pos);
			}
		});
	}

	public static class TileEntitySoundLooper extends BlockEntityClientSerializableMapper implements TickableMapper {

		private String soundID = "";
		private int repeatTick = 20;
		private float soundVolume = 1;
		private int soundCategory = 0;
		private boolean requireRedstone = false;
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
			if (repeatTick > 0 && MTR.isGameTickInterval(repeatTick) && !soundID.isEmpty()) {
				if(level == null || level.isClientSide()) return;
				BlockPos pos = this.worldPosition;

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
			this.soundID = soundId;
			this.repeatTick = interval;
			this.soundCategory = soundCategory;
			this.soundVolume = volume;
			this.requireRedstone = requireRedstone;
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