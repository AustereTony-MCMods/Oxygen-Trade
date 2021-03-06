package austeretony.oxygen_market.server.sync;

import austeretony.oxygen_core.server.api.PrivilegesServer;
import austeretony.oxygen_core.server.sync.DataSyncHandlerServer;
import austeretony.oxygen_market.common.config.MarketConfig;
import austeretony.oxygen_market.common.main.MarketMain;
import austeretony.oxygen_market.common.main.MarketPrivileges;
import austeretony.oxygen_market.common.market.SalesHistoryEntry;
import austeretony.oxygen_market.server.MarketManagerServer;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.UUID;

public class SalesHistorySyncHandlerServer implements DataSyncHandlerServer<SalesHistoryEntry> {

    @Override
    public int getDataId() {
        return MarketMain.DATA_ID_MARKET_HISTORY;
    }

    @Override
    public boolean allowSync(UUID playerUUID) {
        return PrivilegesServer.getBoolean(playerUUID, MarketPrivileges.SALES_HISTORY_ACCESS.getId(),
                MarketConfig.ENABLE_SALES_HISTORY_SYNC.asBoolean());
    }

    @Nonnull
    @Override
    public Map<Long, SalesHistoryEntry> getDataMap(UUID playerUUID) {
        return MarketManagerServer.instance().getSalesHistoryMap();
    }
}
