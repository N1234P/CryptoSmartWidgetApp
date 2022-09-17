package com.example.crypto_bsc_widget;

import java.util.HashMap;
import java.util.Map;

public class CoinGecko {
    private static Map<String, String> symbols = new HashMap<String, String>();


    public static double extractPrice(String response) {
        try {
            response = response.substring(response.indexOf(":") + 1);
            response = response.substring(response.indexOf(":") + 1);
            response = response.substring(0, response.indexOf(","));
            return Double.parseDouble(response);
        }
        catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String extractPercent(String response) {
        try {
            response = response.substring(response.indexOf("usd_24h"));
            response = response.substring(response.indexOf(":") + 1);
            response = response.substring(0, response.indexOf("}"));
            if(response.length() > 5)
                return response.substring(0,4);

            return response;
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return "";

        }
    }

    public static String convert(String id) {
        aggregateMap();
        return symbols.get(id);
    }

    private static void aggregateMap() {
        symbols.put("bitcoin", "btc");
        symbols.put("ethereum", "eth");
        symbols.put("binancecoin", "bnb");
        symbols.put("cardano", "ada");
        symbols.put("ripple", "xrp");
        symbols.put("solana", "sol");
        symbols.put("polkadot", "dot");
        symbols.put("dogecoin", "doge");
        symbols.put("avalanche-2", "avax");
        symbols.put("shiba-inu", "shib");
        symbols.put("matic-network", "matic");
        symbols.put("tron", "trx");
        symbols.put("ethereum-classic", "etc");
        symbols.put("okb", "okb");
        symbols.put("leo-token", "leo");
        symbols.put("near", "near");
        symbols.put("litecoin", "ltc");
        symbols.put("chainlink", "link");
        symbols.put("uniswap", "uni");
        symbols.put("ftx-token", "ftt");
        symbols.put("crypto-com-chain", "cro");
        symbols.put("cosmos", "atom");
        symbols.put("stellar", "xlm");
        symbols.put("flow", "flow");
        symbols.put("monero", "xmr");
        symbols.put("bitcoin-cash", "bch");
        symbols.put("algorand", "algo");
        symbols.put("vechain", "vet");
        symbols.put("filecoin", "fil");
        symbols.put("apecoin", "ape");
        symbols.put("internet-computer", "icp");
        symbols.put("decentraland", "mana");
        symbols.put("hedera-hashgraph", "hbar");
        symbols.put("chain-2", "xcn");
        symbols.put("tezos", "xtz");
        symbols.put("the-sandbox", "sand");
        symbols.put("quant-network", "qnt");
        symbols.put("axie-infinity", "axs");
        symbols.put("theta-token", "theta");
        symbols.put("aave", "aave");
        symbols.put("elrond-erd-2", "egld");
        symbols.put("lido-dao", "ldo");
        symbols.put("frax", "frax");
        symbols.put("eos", "eos");
        symbols.put("helium", "hnt");
        symbols.put("the-graph", "grt");
        symbols.put("celsius-degree-token", "cel");
        symbols.put("kucoin-shares", "kcs");
        symbols.put("fantom", "ftm");
        symbols.put("iota", "miota");
        symbols.put("maker", "mkr");
        symbols.put("havven", "snx");
        symbols.put("bittorrent", "btt");
        symbols.put("klay-token", "klay");
        symbols.put("thorchain", "rune");
        symbols.put("chiliz", "chz");
        symbols.put("neo", "neo");
        symbols.put("huobi-token", "ht");
        symbols.put("bitdao", "bit");
        symbols.put("arweave", "ar");
        symbols.put("gatechain-token", "gt");
        symbols.put("pancakeswap-token", "cake");
        symbols.put("zilliqa", "zil");
        symbols.put("basic-attention-token", "bat");
        symbols.put("blockstack", "stx");
        symbols.put("enjincoin", "enj");
        symbols.put("radix", "xrd");
        symbols.put("amp-token", "amp");
        symbols.put("waves", "waves");
        symbols.put("pax-gold", "paxg");
        symbols.put("dash", "dash");
        symbols.put("stepn", "gmt");
        symbols.put("loopring", "lrc");
        symbols.put("tenset", "10set");
        symbols.put("kava", "kava");
        symbols.put("mina-protocol", "mina");
        symbols.put("kusama", "ksm");
        symbols.put("defichain", "dfi");
        symbols.put("celo", "celo");
        symbols.put("nexo", "nexo");
        symbols.put("osmosis", "osmo");


    }

}
