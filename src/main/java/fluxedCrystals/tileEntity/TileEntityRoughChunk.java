package fluxedCrystals.tileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fluxedCrystals.api.RecipeRegistry;

public class TileEntityRoughChunk extends TileEntity {
	private int idx = 0;
	private long beam1;
	private float beam2;
	

	public TileEntityRoughChunk() {

	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}
	 @SideOnly(Side.CLIENT)
	    public float func_146002_i()
	    {
	        {
	            int i = (int)(this.worldObj.getTotalWorldTime() - this.beam1);
	            this.beam1 = this.worldObj.getTotalWorldTime();

	            if (i > 1)
	            {
	                this.beam2 -= (float)i / 40.0F;

	                if (this.beam2 < 0.0F)
	                {
	                    this.beam2 = 0.0F;
	                }
	            }

	            this.beam2 += 0.025F;

	            if (this.beam2 > 1.0F)
	            {
	                this.beam2 = 1.0F;
	            }

	            return this.beam2;
	        }
	    }
	public int getIndex() {
		return idx;
	}

	public void init(int itemDamage) {
		this.idx = itemDamage;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("index", idx);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.idx = tag.getInteger("index");
	}

	@Override
	public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
		return oldBlock != newBlock;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}
}
