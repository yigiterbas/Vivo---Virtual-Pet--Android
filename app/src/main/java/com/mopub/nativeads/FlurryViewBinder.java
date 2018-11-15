package com.mopub.nativeads;

import android.support.annotation.NonNull;

public class FlurryViewBinder {
    ViewBinder staticViewBinder;
    public int videoViewId;

    private FlurryViewBinder(@NonNull Builder builder) {
        this.staticViewBinder = builder.staticViewBinder;
        this.videoViewId = builder.videoViewId;
    }

    public final static class Builder {
        ViewBinder staticViewBinder;
        int videoViewId;

        public Builder(final ViewBinder staticViewBinder) {
            this.staticViewBinder = staticViewBinder;
        }

        @NonNull
        public final Builder videoViewId(final int videoViewId) {
            this.videoViewId = videoViewId;
            return this;
        }

        @NonNull
        public final FlurryViewBinder build() {
            return new FlurryViewBinder(this);
        }
    }
}
