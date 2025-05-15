package madex.world.app;

import android.content.res.Resources;
import android.os.Bundle;

import java.util.Objects;

import sspnet.tech.core.AdPayload;
import sspnet.tech.core.BannerListener;
import sspnet.tech.unfiled.AdException;
import sspnet.tech.core.BannerSettings;
import sspnet.tech.unfiled.ExternalInfoStrings;
import sspnet.tech.madex.Madex;

public class BannerActivity extends AdvertActivity implements BannerListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_layout);
        setPlacementName(EnvironmentVariables.madexBannerUnitID);
        setMediation();
        Madex.setBannerListener(this);
        Madex.setCustomParams(ExternalInfoStrings.yandexBannerUnitID, "demo-banner-yandex");
    }

    @Override
    public void onBannerLoaded(AdPayload adPayload) {
        addLog("onBannerLoaded: Ad loaded and ready to show.");
    }

    @Override
    public void onBannerLoadFailed(AdPayload adPayload, AdException error) {
        addLog("onBannerLoadFail: Ad was not loaded." + error.getDescription() + " " + error.getCaused() + ".");
    }

    @Override
    public void onBannerShown(AdPayload adPayload) {
        addLog("onBannerShown: Ad shown.");
    }

    @Override
    public void onBannerShowFailed(AdPayload adPayload, AdException error) {
        addLog("onBannerShowFailed: Ad was not shown." + error.getDescription() + " " + error.getCaused() + ".");
    }

    @Override
    public void onBannerClosed(AdPayload adPayload) {
        addLog("onBannerClosed:  Ad closed.");
    }

    @Override
    public void onBannerImpression(AdPayload adPayload) {
        addLog("onBannerImpression: Banner get impression.");
    }

    @Override
    public void selectPlacementName(String network) {
        final Resources resources = getResources();
        final String madex = resources.getString(R.string.madex);
        final String yandex = resources.getString(R.string.yandex);
        final String ironsource = resources.getString(R.string.ironsource);
        final String mintegral = resources.getString(R.string.mintegral);

        if (Objects.equals(network, madex)) {
            setPlacementName(EnvironmentVariables.madexBannerUnitID);
        } else {
            setPlacementName("");
        }
    }

    @Override
    public void loadAd() {
        if (Madex.canLoadAd(Madex.BANNER, getPlacementName())) {
            addLog("Ad start to load.");
            final BannerSettings settings =  new BannerSettings.Builder()
                    .setShowCloseButton(true)
                    .setBannerLayoutID(R.id.banner_view)
                    .build();
            Madex.setCustomParams(ExternalInfoStrings.yandexBannerWidth, findViewById(R.id.banner_view).getWidth());
            Madex.setBannerCustomSettings(settings);
            Madex.loadAd(this, Madex.BANNER, getPlacementName());
        } else {
            addLog("SDK can't start load ad.");
        }
    }

    @Override
    public void showAd() {
        if (Madex.isAdLoaded(Madex.BANNER, getPlacementName())) {
            Madex.showAd(this, Madex.BANNER, getPlacementName());
        } else {
            addLog("Ad is not loaded yet");
        }
    }

    @Override
    public void destroyAd() {
        Madex.destroyAd(Madex.BANNER, getPlacementName());
        addLog("Ad was destroyed.");
    }
}