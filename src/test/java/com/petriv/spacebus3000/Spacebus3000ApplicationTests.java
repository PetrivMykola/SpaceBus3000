package com.petriv.spacebus3000;

import com.petriv.spacebus3000.service.SpaceBusService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Spacebus3000ApplicationTests {

	@Autowired
	private SpaceBusService spaceBusService;

    private final String HEAVY = "Heavy Element Spacemine”";
    private final String GRAND = "Grand Nebula Spaceport";
    private final String ASTEROID = "Asteroid Research Institute";
    private final String OORT = "Oort Cloud Observation Facility";

	@Test
	public void testFromAsteroidToGrand() {
	    boolean result = spaceBusService.isConnection(ASTEROID, GRAND);
		Assert.assertTrue(result);
	}

	@Test
	public void testFromAsteroidToHeavy() {
        boolean result = spaceBusService.isConnection(ASTEROID, HEAVY);
        Assert.assertFalse(result);
    }

    @Test
    public void testFromOopToAsteroid() {
	    boolean result = spaceBusService.isConnection(OORT, ASTEROID);
        Assert.assertTrue(result);
    }
}

