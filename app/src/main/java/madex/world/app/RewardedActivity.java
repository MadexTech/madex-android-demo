package madex.world.app;

import android.content.res.Resources;
import android.os.Bundle;

import java.util.Objects;

import sspnet.tech.core.AdPayload;
import sspnet.tech.core.RewardedListener;
import sspnet.tech.unfiled.AdException;
import sspnet.tech.madex.Madex;
import sspnet.tech.unfiled.ExternalInfoStrings;

public class RewardedActivity extends AdvertActivity implements RewardedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);
        setPlacementName(EnvironmentVariables.madexRewardedUnitID);
        setMediation();
        Madex.setRewardedListener(this);
    }

    @Override
    public void onRewardedLoaded(AdPayload adPayload) {
        addLog("onRewardedLoaded: Ad loaded and ready to show.");
    }

    @Override
    public void onRewardedLoadFail(AdPayload adPayload, AdException error) {
        addLog("onRewardedLoadFail: Ad was not loaded." + error.getDescription() + " " + error.getCaused() + ".");
    }

    @Override
    public void onRewardedShown(AdPayload adPayload) {
        addLog("onRewardedShown: Ad shown.");
    }

    @Override
    public void onRewardedShowFailed(AdPayload adPayload, AdException error) {
        addLog("onRewardedShowFailed: Ad was not shown." + error.getDescription() + " " + error.getCaused() + ".");
    }

    @Override
    public void onRewardedClosed(AdPayload adPayload) {
        addLog("onRewardedClosed: Ad closed.");
    }

    @Override
    public void onRewardedFinished(AdPayload adPayload) {
        addLog("onRewardedFinished: Ad was finished.");
    }

    @Override
    public void selectPlacementName(String network) {
        final Resources resources = getResources();
        final String madex = resources.getString(R.string.madex);
        final String yandex = resources.getString(R.string.yandex);
        final String ironsource = resources.getString(R.string.ironsource);
        final String mintegral = resources.getString(R.string.mintegral);
        final String applovin = resources.getString(R.string.applovin);

        if(Objects.equals(network, madex)) {
            setPlacementName(EnvironmentVariables.madexRewardedUnitID);
            Madex.setCustomParams(ExternalInfoStrings.applovinRewardedUnitID, null);
        }else if(Objects.equals(network, yandex)) {
            setPlacementName(EnvironmentVariables.yandexRewardedlUnitID);
            Madex.setCustomParams(ExternalInfoStrings.applovinRewardedUnitID, null);
        }else if(Objects.equals(network, ironsource)) {
            setPlacementName(EnvironmentVariables.ironsourceRewardedlUnitID);
            Madex.setCustomParams(ExternalInfoStrings.applovinRewardedUnitID, null);
        }else if(Objects.equals(network, mintegral)) {
            setPlacementName(EnvironmentVariables.mintegraleRewardedlUnitID);
            Madex.setCustomParams(ExternalInfoStrings.applovinRewardedUnitID, null);
        }else if(Objects.equals(network, applovin)) {
            setPlacementName(EnvironmentVariables.ironsourceInterstitialUnitID);
            Madex.setCustomParams(ExternalInfoStrings.applovinInterstitialUnitID, "123");
        }
    }

    @Override
    public void loadAd() {
        if (Madex.canLoadAd(Madex.REWARDED, getPlacementName())) {
            addLog("Ad start to load.");
            Madex.loadAd(this, Madex.REWARDED, getPlacementName());
        } else {
            addLog("SDK can't start load ad.");
        }
    }

    @Override
    public void showAd() {
        if (Madex.isAdLoaded(Madex.REWARDED, getPlacementName())) {
            Madex.showAd(this, Madex.REWARDED, getPlacementName());
        } else {
            addLog("Ad is not loaded yet");
        }
    }

    @Override
    public void destroyAd() {
        Madex.destroyAd(Madex.REWARDED, getPlacementName());
        addLog("Ad was destroyed.");
    }
}