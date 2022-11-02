package com.github.manocomandante.redefastly.lobby.model;

import org.bukkit.inventory.*;

public class ServerModel
{
    private final String bungeeServerName;
    private final String commandOnClick;
    private final int inventorySlot;
    private final boolean lobby;
    private ItemStack icon;
    
    public ServerModel(final String bungeeServerName, final String commandOnClick, final int inventorySlot, final boolean lobby) {
        this.bungeeServerName = bungeeServerName;
        this.commandOnClick = commandOnClick;
        this.inventorySlot = inventorySlot;
        this.lobby = lobby;
    }
    
    public String getBungeeServerName() {
        return this.bungeeServerName;
    }
    
    public String getCommandOnClick() {
        return this.commandOnClick;
    }
    
    public int getInventorySlot() {
        return this.inventorySlot;
    }
    
    public boolean isLobby() {
        return this.lobby;
    }
    
    public ItemStack getIcon() {
        return this.icon;
    }
    
    public void setIcon(final ItemStack icon) {
        this.icon = icon;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ServerModel)) {
            return false;
        }
        final ServerModel other = (ServerModel)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$bungeeServerName = this.getBungeeServerName();
        final Object other$bungeeServerName = other.getBungeeServerName();
        Label_0065: {
            if (this$bungeeServerName == null) {
                if (other$bungeeServerName == null) {
                    break Label_0065;
                }
            }
            else if (this$bungeeServerName.equals(other$bungeeServerName)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$commandOnClick = this.getCommandOnClick();
        final Object other$commandOnClick = other.getCommandOnClick();
        Label_0102: {
            if (this$commandOnClick == null) {
                if (other$commandOnClick == null) {
                    break Label_0102;
                }
            }
            else if (this$commandOnClick.equals(other$commandOnClick)) {
                break Label_0102;
            }
            return false;
        }
        if (this.getInventorySlot() != other.getInventorySlot()) {
            return false;
        }
        if (this.isLobby() != other.isLobby()) {
            return false;
        }
        final Object this$icon = this.getIcon();
        final Object other$icon = other.getIcon();
        if (this$icon == null) {
            if (other$icon == null) {
                return true;
            }
        }
        else if (this$icon.equals(other$icon)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ServerModel;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $bungeeServerName = this.getBungeeServerName();
        result = result * 59 + (($bungeeServerName == null) ? 43 : $bungeeServerName.hashCode());
        final Object $commandOnClick = this.getCommandOnClick();
        result = result * 59 + (($commandOnClick == null) ? 43 : $commandOnClick.hashCode());
        result = result * 59 + this.getInventorySlot();
        result = result * 59 + (this.isLobby() ? 79 : 97);
        final Object $icon = this.getIcon();
        result = result * 59 + (($icon == null) ? 43 : $icon.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ServerModel(bungeeServerName=" + this.getBungeeServerName() + ", commandOnClick=" + this.getCommandOnClick() + ", inventorySlot=" + this.getInventorySlot() + ", lobby=" + this.isLobby() + ", icon=" + this.getIcon() + ")";
    }
}
