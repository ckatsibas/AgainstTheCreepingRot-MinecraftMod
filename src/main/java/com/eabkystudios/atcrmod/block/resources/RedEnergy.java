package com.eabkystudios.atcrmod.block.resources;

import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class RedEnergy extends SimpleEnergyHandler {
    public RedEnergy(int capacity, int receive, int extract) {
        super(capacity, receive, extract);
    }

    public int getEnergyStored() {
        return getAmountAsInt();
    }

    public int getMaxEnergyStored() {
        return getCapacityAsInt();
    }

    public int extractEnergy(int amount, boolean simulate) {
        try (var transaction = Transaction.openRoot()) {
            int extracted = extract(amount, transaction);
            if (!simulate) transaction.commit();
            return extracted;
        }
    }

    public int receiveEnergy(int amount, boolean simulate) {
        try (var transaction = Transaction.openRoot()) {
            int received = insert(amount, transaction);
            if (!simulate) transaction.commit();
            return received;
        }
    }
}
