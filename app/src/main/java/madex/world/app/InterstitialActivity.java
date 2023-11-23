package madex.world.app;

import android.content.res.Resources;
import android.os.Bundle;

import java.util.Objects;

import sspnet.tech.core.InterstitialListener;
import sspnet.tech.unfiled.AdException;
import sspnet.tech.madex.Madex;
public class InterstitialActivity extends AdvertActivity implements InterstitialListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);
        setPlacementName(EnvironmentVariables.madexInterstitialUnitID);
        setMediation();
        Madex.setInterstitialListener(this);
    }

    @Override
    public void onInterstitialLoaded() {
        addLog("onInterstitialLoaded: Ad loaded and ready to show.");
    }

    @Override
    public void onInterstitialLoadFail(AdException error) {
        addLog("onInterstitialLoadFail: Ad was not loaded." + error.getDescription() + " " + error.getCaused() + ".");
    }

    @Override
    public void onInterstitialShown() {
        addLog("onInterstitialShown: Ad shown.");
    }

    @Override
    public void onInterstitialShowFailed(AdException error) {
        addLog("onInterstitialShowFailed: Ad was not shown." + error.getDescription() + " " + error.getCaused() + ".");
    }

    @Override
    public void onInterstitialClosed() {
        addLog("onInterstitialClosed: Ad closed.");
    }

    @Override
    public void selectPlacementName(String network) {
        final Resources resources = getResources();
        final String madex = resources.getString(R.string.madex);
        final String yandex = resources.getString(R.string.yandex);
        final String ironsource = resources.getString(R.string.ironsource);
        final String mintegral = resources.getString(R.string.mintegral);

        if(Objects.equals(network, madex)) {
            setPlacementName(EnvironmentVariables.madexInterstitialUnitID);
        }else if(Objects.equals(network, yandex)) {
            setPlacementName(EnvironmentVariables.yandexInterstitialUnitID);
        }else if(Objects.equals(network, ironsource)) {
            setPlacementName(EnvironmentVariables.ironsourceInterstitialUnitID);
        }else if(Objects.equals(network, mintegral)) {
            setPlacementName(EnvironmentVariables.mintegralInterstitialUnitID);
        }
    }

    @Override
    public void loadAd() {
        if (Madex.canLoadAd(Madex.INTERSTITIAL, getPlacementName())) {
            addLog("Ad start to load.");
            Madex.loadAd(this, Madex.INTERSTITIAL, getPlacementName());
        } else {
            addLog("SDK can't start load ad.");
        }
    }

    @Override
    public void showAd() {
        if (Madex.isAdLoaded(Madex.INTERSTITIAL, getPlacementName())) {
            Madex.showAd(this, Madex.INTERSTITIAL, getPlacementName());
        } else {
            addLog("Ad is not loaded yet");
        }
    }

    @Override
    public void destroyAd() {
        Madex.destroyAd(Madex.INTERSTITIAL, getPlacementName());
        addLog("Ad was destroyed.");
    }
}