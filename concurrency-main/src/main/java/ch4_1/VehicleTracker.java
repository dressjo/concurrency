package ch4_1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;

public class VehicleTracker {

	@GuardedBy("this")
	private final Map<String, MutablePoint> locations;

	public VehicleTracker(Map<String, MutablePoint> locations) {
		this.locations = deepCopy(locations);
	}

	public Map<String, MutablePoint> getLocations() {
		return deepCopy(locations);
	}

	public MutablePoint getLocation(String id, int x, int y) {
		MutablePoint location = locations.get(id);
		if (location == null) {
			throw new IllegalArgumentException("No such id found");
		}
		return location;
	}

	public void setLocation(String id, int x, int y) {
		MutablePoint location = getLocation(id, x, y);
		if (location == null) {
			throw new IllegalArgumentException("No such id found");
		}
		location.x = x;
		location.y = y;

	}

	private static Map<String, MutablePoint> deepCopy(
			Map<String, MutablePoint> m) {
		Map<String, MutablePoint> mapCopy = new HashMap<String, MutablePoint>();

		for (String key : m.keySet()) {
			mapCopy.put(key, new MutablePoint(m.get(key).x, m.get(key).y));
		}
		return Collections.unmodifiableMap(mapCopy);
	}
	

}
