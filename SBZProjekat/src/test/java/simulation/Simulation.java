package simulation;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbz.projekat.SBZProjekat.simulation.HeartBeatEvent;

public class Simulation {
	
	@Test
	public void simulation1() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10000);
		KieSession ksession = kContainer.newKieSession("eventSessionPseudoClock");
		SessionPseudoClock clock = ksession.getSessionClock();
		
		Random rand = new Random();
        for (int index = 0; index < 100; index++) {
        	 int count = rand.nextInt(5);
        	 for(int i = 0; i<count; i++) {
	    		HeartBeatEvent beep = new HeartBeatEvent();
	        	ksession.insert(beep);	
        	 }
        	 clock.advanceTime(1, TimeUnit.SECONDS);
	         int ruleCount = ksession.fireAllRules();
        }
	}
	
	@Test
	public void simulation2() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10000);
		KieSession ksession = kContainer.newKieSession("eventSessionPseudoClock");
		SessionPseudoClock clock = ksession.getSessionClock();
		
		Random rand = new Random();
        for (int index = 0; index < 100; index++) {
        	 int count = rand.nextInt(5);
        	 for(int i = 0; i<count; i++) {
	    		HeartBeatEvent beep = new HeartBeatEvent();
	        	ksession.insert(beep);	
        	 }
        	 clock.advanceTime(1, TimeUnit.SECONDS);
	         int ruleCount = ksession.fireAllRules();
        }
	}

}
