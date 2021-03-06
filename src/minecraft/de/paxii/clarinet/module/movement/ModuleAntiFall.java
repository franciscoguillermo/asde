package de.paxii.clarinet.module.movement;

import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.event.events.player.PlayerMoveEvent;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.module.ModuleCategory;
import de.paxii.clarinet.util.module.killaura.TimeManager;
import de.paxii.clarinet.util.module.settings.ValueBase;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class ModuleAntiFall extends Module {
	private final TimeManager timeManager;
	private BlockPos savedPosition;

	public ModuleAntiFall() {
		super("AntiFall", ModuleCategory.MOVEMENT, -1);

		this.setRegistered(true);
		this.setDescription("Prevents you from falling more than 3 blocks. Requires NoCheatPlus.");
		this.getModuleValues().put("fallDistance", new ValueBase("AntiFall Distance", 2.0F, 1.0F, 10.0F, "Distance"));

		this.timeManager = new TimeManager();
	}

	@EventHandler
	public void onPlayerMovement(PlayerMoveEvent event) {
		if (event.getPlayer().fallDistance >= this.getValueBase("fallDistance").getValue()
				&& !event.getPlayer().onGround
				&& !event.getPlayer().isSneaking() && !isOverBlock()) {
			event.setMotionY(0.0D);

			if (this.savedPosition != null) {
				if (this.timeManager.sleep(150L)) {
					if (this.savedPosition.getY() != event.getPlayer().posY) {
						event.getPlayer().fallDistance = 0.0F;

						this.savedPosition = null;
					}

					this.timeManager.updateLast();
				}
			} else {
				this.savedPosition = event.getPlayer().getPosition();
			}
		}

		this.timeManager.updateTimer();
	}

	private boolean isOverBlock() {
		for (int i = 1; i < 2; i++) {
			Block block = Wrapper.getWorld().getBlock(
					(int) Wrapper.getPlayer().posX,
					(int) Wrapper.getPlayer().posY - i,
					(int) Wrapper.getPlayer().posZ);
			if (Block.getIdFromBlock(block) != 0) {
				return true;
			}
		}

		return false;
	}
}
