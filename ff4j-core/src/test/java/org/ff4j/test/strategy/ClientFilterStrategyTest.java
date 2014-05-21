package org.ff4j.test.strategy;

import java.text.ParseException;

import junit.framework.Assert;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.strategy.ClientFilterStrategy;
import org.ff4j.test.AbstractFf4jTest;
import org.junit.Test;

/**
 * Test for {@link ClientFilterStrategy} pass if client is int the whitelist.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class ClientFilterStrategyTest extends AbstractFf4jTest {

    @Override
    public FF4j initFF4j() {
        return new FF4j("test-clientFilterStrategy.xml");
    }

    @Test
    public void testFilterOK() throws ParseException {
        // Given
        Feature f1 = ff4j.getFeature(F1);
        Assert.assertNotNull(f1.getFlippingStrategy());
        org.ff4j.strategy.ClientFilterStrategy cStra = (ClientFilterStrategy) f1.getFlippingStrategy();
        Assert.assertNotNull(cStra.getInitParams());
        Assert.assertEquals(1, cStra.getInitParams().size());
        Assert.assertEquals(3, cStra.getGrantedClient().size());
        Assert.assertTrue(cStra.getGrantedClient().contains("pierre"));
        Assert.assertFalse(cStra.getGrantedClient().contains(FEATURE_NEW));
        Assert.assertTrue(f1.isEnable());

        // When (add correct client name)
        FlippingExecutionContext fex = new FlippingExecutionContext();
        fex.addValue(ClientFilterStrategy.CLIENT_HOSTNAME, "pierre");

        // Then
        Assert.assertTrue(ff4j.check(F1, fex));
    }

    @Test
    public void testFilterInvalidClient() throws ParseException {
        // Given
        Feature f1 = ff4j.getFeature(F1);
        Assert.assertNotNull(f1.getFlippingStrategy());
        org.ff4j.strategy.ClientFilterStrategy cStra = (ClientFilterStrategy) f1.getFlippingStrategy();
        Assert.assertNotNull(cStra.getInitParams());
        Assert.assertEquals(1, cStra.getInitParams().size());
        Assert.assertEquals(3, cStra.getGrantedClient().size());
        Assert.assertTrue(cStra.getGrantedClient().contains("pierre"));
        Assert.assertFalse(cStra.getGrantedClient().contains(FEATURE_NEW));
        Assert.assertTrue(f1.isEnable());


        // When (add invalid client name)
        FlippingExecutionContext fex = new FlippingExecutionContext();
        fex.addValue(ClientFilterStrategy.CLIENT_HOSTNAME, FEATURE_NEW);

        // Then
        Assert.assertFalse(ff4j.check(F1, fex));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFilterRequiredContext() {

        // Given
        Feature f1 = ff4j.getFeature(F1);
        Assert.assertNotNull(f1.getFlippingStrategy());
        org.ff4j.strategy.ClientFilterStrategy cStra = (ClientFilterStrategy) f1.getFlippingStrategy();
        Assert.assertNotNull(cStra.getInitParams());
        Assert.assertEquals(1, cStra.getInitParams().size());
        Assert.assertEquals(3, cStra.getGrantedClient().size());
        Assert.assertTrue(cStra.getGrantedClient().contains("pierre"));
        Assert.assertFalse(cStra.getGrantedClient().contains(FEATURE_NEW));
        Assert.assertTrue(f1.isEnable());

        // Then FeatureContext is requires
        ff4j.check(F1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFilterRequiredClientHostName() {

        // Given
        Feature f1 = ff4j.getFeature(F1);
        Assert.assertNotNull(f1.getFlippingStrategy());
        org.ff4j.strategy.ClientFilterStrategy cStra = (ClientFilterStrategy) f1.getFlippingStrategy();
        Assert.assertNotNull(cStra.getInitParams());
        Assert.assertEquals(1, cStra.getInitParams().size());
        Assert.assertEquals(3, cStra.getGrantedClient().size());
        Assert.assertTrue(cStra.getGrantedClient().contains("pierre"));
        Assert.assertFalse(cStra.getGrantedClient().contains(FEATURE_NEW));
        Assert.assertTrue(f1.isEnable());

        // When
        FlippingExecutionContext fex = new FlippingExecutionContext();
        fex.addValue(FEATURE_NEW, FEATURE_NEW);

        // Then
        ff4j.check(F1, fex);
    }

}