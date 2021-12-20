package planeflight.environment;

public final class World {
    public static float temperature = 17.4f;
    public static int month = Month.JANUARY.getNumber();
    public static int day = 5;
    /**
    * Time, defined in seconds since midnight
    * Default value: 7 hours, 15 minutes
    */
    public static int time = (7 * 60 + 15) * 60;
    public static Weather weather = Weather.SUNNY;

    private static final int SECONDS_IN_DAY = 24 * 60 * 60;
    private static final int DAYS_IN_MONTH = 30;

    public static final Location[] LOCATIONS = {Location.NOTHING, Location.SNOW_PILLAR, Location.MOUNTAIN,
                                                Location.NOTHING, Location.MINE, Location.NOTHING};
    
    /**
     * Updates time
     * @param seconds number of seconds to append to the clock
     */
    public static void updateTime(int seconds) {
        time += seconds;
        if (time >= SECONDS_IN_DAY) {
            day += time / SECONDS_IN_DAY;
            time = time % SECONDS_IN_DAY;
        }
        if (day >= DAYS_IN_MONTH) {
            month += day / DAYS_IN_MONTH;
            day = day % DAYS_IN_MONTH;
        }
        if (month >= 12) {
            month = month % 12;
        }
    }

    /**
     * Describes the environment
     */
    public static void describe() {
        String result = "The day was ";
    }
}