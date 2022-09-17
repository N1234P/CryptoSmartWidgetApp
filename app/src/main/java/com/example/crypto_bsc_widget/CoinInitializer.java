package com.example.crypto_bsc_widget;

import java.util.ArrayList;
import java.util.List;

public class CoinInitializer {
    private List<Crypto> coins = new ArrayList<Crypto>();

    public CoinInitializer() {
        populateCoins();
    }

    private void populateCoins() {


        Crypto btc = new Crypto("bitcoin");
        btc.setLogoCoinId(R.drawable.crypto_btc);
        coins.add(btc);

        Crypto eth = new Crypto("ethereum");
        eth.setLogoCoinId(R.drawable.crypto_eth);
        coins.add(eth);

        Crypto bnb = new Crypto("binancecoin");
        bnb.setLogoCoinId(R.drawable.crypto_bnb);
        coins.add(bnb);

        Crypto xrp = new Crypto("ripple");
        xrp.setLogoCoinId(R.drawable.crypto_xrp);
        coins.add(xrp);

        Crypto ada = new Crypto("cardano");
        ada.setLogoCoinId(R.drawable.crypto_ada);
        coins.add(ada);

        Crypto sol = new Crypto("solana");
        sol.setLogoCoinId(R.drawable.crypto_sol);
        coins.add(sol);

        Crypto dot = new Crypto("polkadot");
        dot.setLogoCoinId(R.drawable.crypto_dot);
        coins.add(dot);

        Crypto doge = new Crypto("dogecoin");
        doge.setLogoCoinId(R.drawable.crypto_doge);
        coins.add(doge);

        Crypto avax = new Crypto("avalanche-2");
        avax.setLogoCoinId(R.drawable.crypto_avax);
        coins.add(avax);

        Crypto shib = new Crypto("shiba-inu");
        shib.setLogoCoinId(R.drawable.crypto_shib);
        coins.add(shib);

        Crypto matic = new Crypto("matic-network");
        matic.setLogoCoinId(R.drawable.crypto_matic);
        coins.add(matic);

        Crypto trx = new Crypto("tron");
        trx.setLogoCoinId(R.drawable.crypto_trx);
        coins.add(trx);

        Crypto etc = new Crypto("ethereum-classic");
        etc.setLogoCoinId(R.drawable.crypto_etc);
        coins.add(etc);

        Crypto okb = new Crypto("okb");
        okb.setLogoCoinId(R.drawable.crypto_okb);
        coins.add(okb);

        Crypto leo = new Crypto("leo-token");
        leo.setLogoCoinId(R.drawable.crypto_leo);
        coins.add(leo);

        Crypto near = new Crypto("near");
        near.setLogoCoinId(R.drawable.crypto_near);
        coins.add(near);

        Crypto ltc = new Crypto("litecoin");
        ltc.setLogoCoinId(R.drawable.crypto_ltc);
        coins.add(ltc);

        Crypto link = new Crypto("chainlink");
        link.setLogoCoinId(R.drawable.crypto_link);
        coins.add(link);

        Crypto uni = new Crypto("uniswap");
        uni.setLogoCoinId(R.drawable.crypto_uni);
        coins.add(uni);

        Crypto ftt = new Crypto("ftx-token");
        ftt.setLogoCoinId(R.drawable.crypto_ftt);
        coins.add(ftt);

        Crypto cro = new Crypto("crypto-com-chain");
        cro.setLogoCoinId(R.drawable.crypto_cro);
        coins.add(cro);

        Crypto atom = new Crypto("cosmos");
        atom.setLogoCoinId(R.drawable.crypto_atom);
        coins.add(atom);

        Crypto xlm = new Crypto("stellar");
        xlm.setLogoCoinId(R.drawable.crypto_xlm);
        coins.add(xlm);

        Crypto flow = new Crypto("flow");
        flow.setLogoCoinId(R.drawable.crypto_flow);
        coins.add(flow);

        Crypto xmr = new Crypto("monero");
        xmr.setLogoCoinId(R.drawable.crypto_xmr);
        coins.add(xmr);

        Crypto bch = new Crypto("bitcoin-cash");
        bch.setLogoCoinId(R.drawable.crypto_bch);
        coins.add(bch);

        Crypto algo = new Crypto("algorand");
        algo.setLogoCoinId(R.drawable.crypto_algo);
        coins.add(algo);

        Crypto vet = new Crypto("vechain");
        vet.setLogoCoinId(R.drawable.crypto_vet);
        coins.add(vet);

        Crypto fil = new Crypto("filecoin");
        fil.setLogoCoinId(R.drawable.crypto_fil);
        coins.add(fil);

        Crypto ape = new Crypto("apecoin");
        ape.setLogoCoinId(R.drawable.crypto_ape);
        coins.add(ape);

        Crypto icp = new Crypto("internet-computer");
        icp.setLogoCoinId(R.drawable.crypto_icp);
        coins.add(icp);

        Crypto mana = new Crypto("decentraland");
        mana.setLogoCoinId(R.drawable.crypto_mana);
        coins.add(mana);

        Crypto hbar = new Crypto("hedera-hashgraph");
        hbar.setLogoCoinId(R.drawable.crypto_hbar);
        coins.add(hbar);

        Crypto xcn = new Crypto("chain-2");
        xcn.setLogoCoinId(R.drawable.crypto_xcn);
        coins.add(xcn);

        Crypto xtz = new Crypto("tezos");
        xtz.setLogoCoinId(R.drawable.crypto_xtz);
        coins.add(xtz);

        Crypto sand = new Crypto("the-sandbox");
        sand.setLogoCoinId(R.drawable.crypto_sand);
        coins.add(sand);

        Crypto qnt = new Crypto("quant-network");
        qnt.setLogoCoinId(R.drawable.crypto_qnt);
        coins.add(qnt);

        Crypto axs = new Crypto("axie-infinity");
        axs.setLogoCoinId(R.drawable.crypto_axs);
        coins.add(axs);

        Crypto theta = new Crypto("theta-token");
        theta.setLogoCoinId(R.drawable.crypto_theta);
        coins.add(theta);

        Crypto aave = new Crypto("aave");
        aave.setLogoCoinId(R.drawable.crypto_aave);
        coins.add(aave);

        Crypto egld = new Crypto("elrond-erd-2");
        egld.setLogoCoinId(R.drawable.crypto_egld);
        coins.add(egld);

        Crypto ldo = new Crypto("lido-dao");
        ldo.setLogoCoinId(R.drawable.crypto_ldo);
        coins.add(ldo);

        Crypto frax = new Crypto("frax");
        frax.setLogoCoinId(R.drawable.crypto_frax);
        coins.add(frax);

        Crypto eos = new Crypto("eos");
        eos.setLogoCoinId(R.drawable.crypto_eos);
        coins.add(eos);

        Crypto hnt = new Crypto("helium");
        hnt.setLogoCoinId(R.drawable.crypto_hnt);
        coins.add(hnt);

        Crypto grt = new Crypto("the-graph");
        grt.setLogoCoinId(R.drawable.crypto_grt);
        coins.add(grt);

        Crypto cel = new Crypto("celsius-degree-token");
        cel.setLogoCoinId(R.drawable.crypto_cel);
        coins.add(cel);

        Crypto kcs = new Crypto("kucoin-shares");
        kcs.setLogoCoinId(R.drawable.crypto_kcs);
        coins.add(kcs);

        Crypto ftm = new Crypto("fantom");
        ftm.setLogoCoinId(R.drawable.crypto_ftm);
        coins.add(ftm);

        Crypto miota = new Crypto("iota");
        miota.setLogoCoinId(R.drawable.crypto_miota);
        coins.add(miota);

        Crypto mkr = new Crypto("maker");
        mkr.setLogoCoinId(R.drawable.crypto_mkr);
        coins.add(mkr);

        Crypto snx = new Crypto("havven");
        snx.setLogoCoinId(R.drawable.crypto_snx);
        coins.add(snx);

        Crypto btt = new Crypto("bittorrent");
        btt.setLogoCoinId(R.drawable.crypto_btt);
        coins.add(btt);

        Crypto klay = new Crypto("klay-token");
        klay.setLogoCoinId(R.drawable.crypto_klay);
        coins.add(klay);

        Crypto rune = new Crypto("thorchain");
        rune.setLogoCoinId(R.drawable.crypto_rune);
        coins.add(rune);

        Crypto chz = new Crypto("chiliz");
        chz.setLogoCoinId(R.drawable.crypto_chz);
        coins.add(chz);

        Crypto neo = new Crypto("neo");
        neo.setLogoCoinId(R.drawable.crypto_neo);
        coins.add(neo);

        Crypto ht = new Crypto("huobi-token");
        ht.setLogoCoinId(R.drawable.crypto_ht);
        coins.add(ht);

        Crypto bit = new Crypto("bitdao");
        bit.setLogoCoinId(R.drawable.crypto_bit);
        coins.add(bit);

        Crypto ar = new Crypto("arweave");
        ar.setLogoCoinId(R.drawable.crypto_ar);
        coins.add(ar);

        Crypto gt = new Crypto("gatechain-token");
        gt.setLogoCoinId(R.drawable.crypto_gt);
        coins.add(gt);

        Crypto cake = new Crypto("pancakeswap-token");
        cake.setLogoCoinId(R.drawable.crypto_cake);
        coins.add(cake);

        Crypto zil = new Crypto("zilliqa");
        zil.setLogoCoinId(R.drawable.crypto_zil);
        coins.add(zil);

        Crypto bat = new Crypto("basic-attention-token");
        bat.setLogoCoinId(R.drawable.crypto_bat);
        coins.add(bat);

        Crypto enj = new Crypto("enjincoin");
        enj.setLogoCoinId(R.drawable.crypto_enj);
        coins.add(enj);

        Crypto xrd = new Crypto("radix");
        xrd.setLogoCoinId(R.drawable.crypto_xrd);
        coins.add(xrd);

        Crypto amp = new Crypto("amp-token");
        amp.setLogoCoinId(R.drawable.crypto_amp);
        coins.add(amp);

        Crypto waves = new Crypto("waves");
        waves.setLogoCoinId(R.drawable.crypto_waves);
        coins.add(waves);

        Crypto paxg = new Crypto("pax-gold");
        paxg.setLogoCoinId(R.drawable.crypto_paxg);
        coins.add(paxg);

        Crypto dash = new Crypto("dash");
        dash.setLogoCoinId(R.drawable.crypto_dash);
        coins.add(dash);

        Crypto gmt = new Crypto("stepn");
        gmt.setLogoCoinId(R.drawable.crypto_gmt);
        coins.add(gmt);

        Crypto lrc = new Crypto("loopring");
        lrc.setLogoCoinId(R.drawable.crypto_lrc);
        coins.add(lrc);

        Crypto tenset = new Crypto("tenset");
        tenset.setLogoCoinId(R.drawable.crypto_tenset);
        coins.add(tenset);

        Crypto kava = new Crypto("kava");
        kava.setLogoCoinId(R.drawable.crypto_kava);
        coins.add(kava);

        Crypto mina = new Crypto("mina-protocol");
        mina.setLogoCoinId(R.drawable.crypto_mina);
        coins.add(mina);

        Crypto ksm = new Crypto("kusama");
        ksm.setLogoCoinId(R.drawable.crypto_ksm);
        coins.add(ksm);

        Crypto dfi = new Crypto("defichain");
        dfi.setLogoCoinId(R.drawable.crypto_dfi);
        coins.add(dfi);

        Crypto celo = new Crypto("celo");
        celo.setLogoCoinId(R.drawable.crypto_celo);
        coins.add(celo);

        Crypto nexo = new Crypto("nexo");
        nexo.setLogoCoinId(R.drawable.crypto_nexo);
        coins.add(nexo);

        Crypto osmo = new Crypto("osmosis");
        osmo.setLogoCoinId(R.drawable.crypto_osmo);
        coins.add(osmo);

























    }

    public List<Crypto> getCoins() {
        return coins;
    }
}
