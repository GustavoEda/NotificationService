package models;

import java.util.HashMap;
import java.util.Map;

public class RateLimit {
    public final int maxCount;
    public final long windowInSeconds;

    public RateLimit(int maxCount, long windowInSeconds) {
        this.maxCount = maxCount;
        this.windowInSeconds = windowInSeconds;
    }

    public static final Map<EmailType, RateLimit> RATE_LIMIT = new HashMap<>(){{
        put(EmailType.NEWS, new RateLimit(1, 86400));
        put(EmailType.STATUS, new RateLimit(2, 60));
        put(EmailType.MARKETING, new RateLimit(3, 3600));
    }};
}
