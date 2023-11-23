package madex.world.app;

import android.content.res.Resources;
import android.os.Bundle;

import java.util.Objects;

import sspnet.tech.core.RewardedListener;
import sspnet.tech.unfiled.AdException;
import sspnet.tech.madex.Madex;

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
    public void onRewardedLoaded() {
        addLog("onRewardedLoaded: Ad loaded and ready to show.");
    }

    @Override
    public void onRewardedLoadFail(AdException error) {
        addLog("onRewardedLoadFail: Ad was not loaded." + error.getDescription() + " " + error.getCaused() + ".");
    }

    @Override
    public void onRewardedShown() {
        addLog("onRewardedShown: Ad shown.");
    }

    @Override
    public void onRewardedShowFailed(AdException error) {
        addLog("onRewardedShowFailed: Ad was not shown." + error.getDescription() + " " + error.getCaused() + ".");
    }

    @Override
    public void onRewardedClosed() {
        addLog("onRewardedClosed: Ad closed.");
    }

    @Override
    public void onRewardedFinished() {
        addLog("onRewardedFinished: Ad was finished.");
    }

    @Override
    public void selectPlacementName(String network) {
        final Resources resources = getResources();
        final String madex = resources.getString(R.string.madex);
        final String yandex = resources.getString(R.string.yandex);
        final String ironsource = resources.getString(R.string.ironsource);
        final String mintegral = resources.getString(R.string.mintegral);

        if(Objects.equals(network, madex)) {
            setPlacementName(EnvironmentVariables.madexRewardedUnitID);
        }else if(Objects.equals(network, yandex)) {
            setPlacementName(EnvironmentVariables.yandexRewardedlUnitID);
        }else if(Objects.equals(network, ironsource)) {
            setPlacementName(EnvironmentVariables.ironsourceRewardedlUnitID);
        }else if(Objects.equals(network, mintegral)) {
            setPlacementName(EnvironmentVariables.mintegraleRewardedlUnitID);
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