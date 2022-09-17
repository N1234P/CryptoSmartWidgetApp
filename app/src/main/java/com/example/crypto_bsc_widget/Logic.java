package com.example.crypto_bsc_widget;





import android.graphics.Bitmap;





import java.text.DecimalFormat;
import java.util.*;

public class Logic {


    private final List<String> contractAddresses;

    private final List<Crypto> cryptoList;

    private static final String dexRequest = "https://api.dexscreener.com/latest/dex/tokens/";

    private boolean showPercent;

    private final int decimalCount;



    String existingCoinId;

    public Logic(List<String> contractAddresses, String existing, boolean showPercent, String decimalCount) {
        this.contractAddresses = contractAddresses;
        cryptoList = new ArrayList<Crypto>();
        this.existingCoinId = existing;
        this.showPercent = showPercent;
        this.decimalCount = Integer.parseInt(decimalCount);
    }

    public void beginDataRetrieval() {

        Bitmap logo;
        String response;
        String name;
        double price = -1;
        String percentChange = "0%";
        for(String contract : contractAddresses) {
            if(contract.equals(""))
                continue;

            response = UrlRequest.apiCall(dexRequest + contract);
            name = DexScreener.extractTokenId(response);
            response = findLargestLP(response);
            price = DexScreener.extractPrice(response);

            if(showPercent)
                percentChange = DexScreener.extractPercent(response);

            logo = UrlRequest.getLogo("https://assets.coincap.io/assets/icons/" + name.toLowerCase() + "@2x.png");

            if(price != -1) {
                cryptoList.add(new Crypto(contract, price, name, logo, percentChange));
            }

        }
        if(cryptoList.size() == 0) {
            implementExisting();
        }

    }

    public String findLargestLP(String response) {
        String updatedResponse = response;
        int largest = 0;
        String value;
        boolean initial = true;


        while(response.contains("liquidity")) {

            response = response.substring(response.indexOf("liquidity")+ 18);
            value = response.substring(0, response.indexOf(","));
            if(value.contains(".")) {
                value = value.substring(0, value.indexOf("."));
            }

            if(Integer.parseInt(value) > largest) {
                largest = Integer.parseInt(value);
                if(!initial) {
                    updatedResponse = updatedResponse.substring(updatedResponse.indexOf("liquidity"));
                }
                initial = false;
            }
        }

        return updatedResponse;
    }

    private void implementExisting() {
        if(existingCoinId == null || existingCoinId.equals("")) {
            implementDefault();
            return;
        }

        String response = UrlRequest.apiCall("https://api.coingecko.com/api/v3/simple/price?ids=" + existingCoinId + "&vs_currencies=usd&include_24hr_change=true");
        double price = CoinGecko.extractPrice(response);
        String symbol = CoinGecko.convert(existingCoinId);

        String percent = "0";

        if(showPercent)
            percent = CoinGecko.extractPercent(response);

        cryptoList.add(new Crypto(null, price, symbol, UrlRequest.getLogo("https://assets.coincap.io/assets/icons/" + symbol + "@2x.png"), percent));
    }

    private void implementDefault() {

        showPercent = false;

        double priceBtc = DexScreener.extractPrice(UrlRequest.apiCall(dexRequest + "0x152b9d0FdC40C096757F570A51E494bd4b943E50"));
        cryptoList.add(new Crypto("0x321162Cd933E2Be498Cd2267a90534A804051b11", priceBtc, "BTC", UrlRequest.getLogo("https://assets.coincap.io/assets/icons/btc@2x.png"), "0"));

        double priceBnb = DexScreener.extractPrice(UrlRequest.apiCall(dexRequest + "0xbb4CdB9CBd36B01bD1cBaEBF2De08d9173bc095c"));
        cryptoList.add(new Crypto("0xbb4CdB9CBd36B01bD1cBaEBF2De08d9173bc095c", priceBnb, "BNB", UrlRequest.getLogo("https://assets.coincap.io/assets/icons/bnb@2x.png"), "0"));

        double priceEth = DexScreener.extractPrice(UrlRequest.apiCall(dexRequest +"0x74b23882a30290451A17c44f4F05243b6b58C76d"));
        cryptoList.add(new Crypto("0x74b23882a30290451A17c44f4F05243b6b58C76d", priceEth, "ETH", UrlRequest.getLogo("https://assets.coincap.io/assets/icons/eth@2x.png"), "0"));

    }




    public void update() {
        double newPrice;
        String newPercent = "0";
        for(Crypto crypto : cryptoList) {
            if(crypto.getContractAddress() == null) {
                String response = UrlRequest.apiCall("https://api.coingecko.com/api/v3/simple/price?ids=" + existingCoinId + "&vs_currencies=usd&include_24hr_change=true");
                newPrice = CoinGecko.extractPrice(response);

                if(showPercent)
                    newPercent = CoinGecko.extractPercent(response);

            }
            else {
                String response = findLargestLP(UrlRequest.apiCall(dexRequest + crypto.getContractAddress()));
                newPrice = DexScreener.extractPrice(response);

                if(showPercent)
                    newPercent = DexScreener.extractPercent(response);
            }
            if(newPrice < 0)
                return;

            crypto.setPrice(newPrice);
            crypto.setPercent(newPercent);
        }
    }

    public List<Bitmap> getStackLogos() {
        List<Bitmap> bitmapLogo = new ArrayList<Bitmap>();

        for(Crypto crypto : cryptoList) {
            bitmapLogo.add(crypto.getLogo());
        }
        return bitmapLogo;
    }

    public List<String> getData() {
        List<String> result = new ArrayList<String>();

        for(Crypto crypto : cryptoList) {
            StringBuilder decimalPattern = new StringBuilder("#,###.");
            for(int i = 0; i<decimalCount; i++) {
                decimalPattern.append("0");
            }

            DecimalFormat df = new DecimalFormat(decimalPattern.toString());
            String price = df.format(crypto.getPrice());


            if(showPercent)
                result.add(crypto.getSymbol().toUpperCase() + "\n" + "$" + price + "\n" + crypto.getPercent() + "%");

            else
                result.add(crypto.getSymbol().toUpperCase() + "\n" + "$" + price);

        }
        return result;
    }
}

