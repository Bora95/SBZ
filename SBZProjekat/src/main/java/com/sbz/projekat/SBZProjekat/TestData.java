package com.sbz.projekat.SBZProjekat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.map.HashedMap;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Sets;
import com.sbz.projekat.SBZProjekat.diagnose.Diagnose;
import com.sbz.projekat.SBZProjekat.diagnose.DiagnoseDTO;
import com.sbz.projekat.SBZProjekat.diagnose.DiagnoseService;
import com.sbz.projekat.SBZProjekat.disies.Disies;
import com.sbz.projekat.SBZProjekat.disies.DisiesService;
import com.sbz.projekat.SBZProjekat.drug.Drug;
//import com.sbz.projekat.SBZProjekat.drug.Drug;
import com.sbz.projekat.SBZProjekat.drug.DrugService;
import com.sbz.projekat.SBZProjekat.medicalRecord.MedicalRecord;
import com.sbz.projekat.SBZProjekat.medicalRecord.MedicalRecordService;
import com.sbz.projekat.SBZProjekat.resoner.DisiesSymptomsFact;
import com.sbz.projekat.SBZProjekat.resoner.ResonerService;
import com.sbz.projekat.SBZProjekat.substance.Substance;
import com.sbz.projekat.SBZProjekat.substance.SubstanceService;
import com.sbz.projekat.SBZProjekat.symptom.Symptom;
import com.sbz.projekat.SBZProjekat.symptom.SymptomService;
import com.sbz.projekat.SBZProjekat.user.User;
import com.sbz.projekat.SBZProjekat.user.UserService;

@Component
public class TestData {
	
	@Autowired
	private KieContainer kieContainer;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SymptomService symptomService;
	@Autowired
	private DisiesService disiesService;
	@Autowired
	private DrugService drugService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private DiagnoseService diagnoseService;
	@Autowired
	private ResonerService resonerService;
	@Autowired
	private SubstanceService substanceService;
	
	
	@PostConstruct
	private void init() {
		User user1 = new User("admin", "admin", User.Type.ADMIN, "admin", "admin");
		userService.add(user1);
		
		User user2 = userService.add(new User("doktor", "doktor", User.Type.DOCTOR, "doktor", "doktor"));
		User user3 = userService.add(new User("doktorica", "doktorica", User.Type.DOCTOR, "doktorica", "doktorica"));
		
		Substance sub1 = substanceService.add(new Substance("Sastojak1"));
		Substance sub2 = substanceService.add(new Substance("Sastojak2"));
		Substance sub3 = substanceService.add(new Substance("Sastojak3"));
		
		Drug dr1 = drugService.add(new Drug("Antibiotik", Drug.Type.ANTIBIOTIC, Sets.newHashSet(sub2)));
		Drug dr2 = drugService.add(new Drug("Analgetik", Drug.Type.ANALGETIC, Sets.newHashSet(sub1, sub2, sub3)));
		Drug dr3 = drugService.add(new Drug("Ostako", Drug.Type.OTHER, null));
		
		Symptom s1  = symptomService.add(new Symptom("Curenje iz nosa", null, null, null, null, null));
		Symptom s2  = symptomService.add(new Symptom("Bol u grlu", null, null, null, null, null));
		Symptom s3  = symptomService.add(new Symptom("Glavobolja", null, null, null, null, null));
		Symptom s4  = symptomService.add(new Symptom("Kijanje", null, null, null, null, null));
		Symptom s5  = symptomService.add(new Symptom("Kasalj", null, null, null, null, null));
		Symptom s6  = symptomService.add(new Symptom("Temperatura veca od 38 0C", null, null, null, null, null));
		Symptom s7  = symptomService.add(new Symptom("Drhtavica", null, null, null, null, null));
		Symptom s8  = symptomService.add(new Symptom("Bol koji se siri do usiju", null, null, null, null, null));
		Symptom s9  = symptomService.add(new Symptom("Temperatura od 40 0C do 41 0C", null, null, null, null, null));
		Symptom s10 = symptomService.add(new Symptom("Gubitak apetita", null, null, null, null, null));
		Symptom s11 = symptomService.add(new Symptom("Umor", null, null, null, null, null));
		Symptom s12 = symptomService.add(new Symptom("Zuti sekret iz nosa", null, null, null, null, null));
		Symptom s14 = symptomService.add(new Symptom("Visok pritisak", null, null, null, null, null));
		Symptom s15 = symptomService.add(new Symptom("U poslednjih 6 meseci zabeleženo barem 10 slučajeva u kojem je pacijent imao visok pritisak", -15778476000L, 10, null, null, Sets.newHashSet(s14)));
		Symptom s16 = symptomService.add(new Symptom("Cesto uriniranje", null, null, null, null, null));
		Symptom s17 = symptomService.add(new Symptom("Zamor", null, null, null, null, null));
		Symptom s18 = symptomService.add(new Symptom("Mucnina i povracanje", null, null, null, null, null));
		Symptom s19 = symptomService.add(new Symptom("Nocturia", null, null, null, null, null));
		Symptom s20 = symptomService.add(new Symptom("Otoci nogu i zglobova", null, null, null, null, null));
		Symptom s21 = symptomService.add(new Symptom("Gusenje", null, null, null, null, null));
		Symptom s22 = symptomService.add(new Symptom("Dijareja", null, null, null, null, null));
		Symptom s23 = symptomService.add(new Symptom("Bol u grudima", null, null, null, null, null));
		Symptom s24 = symptomService.add(new Symptom("Oporavlja se od operacije", null, null, null, null, null));
		Symptom s25 = symptomService.add(new Symptom("Oticanje oko ociju", null, null, null, null, null));
		Symptom s26 = symptomService.add(new Symptom("Gubitak telesne tezine", null, null, null, null, null));
		Symptom s27 = symptomService.add(new Symptom("U poslednjih 14 dana dijagnostikovana bolest koja kao simptom ima povišenu telesnu temperaturu", -1209600000L, null, null, null, Sets.newHashSet(s6, s9)));
		
		Disies d1 = disiesService.add(new Disies("Prehlada", Sets.newHashSet(s1, s2, s3, s4, s5), null, Disies.Type.TYPE1));
		Disies d2 = disiesService.add(new Disies("Groznica", Sets.newHashSet(s4, s2, s5, s6, s1, s3, s7), null, Disies.Type.TYPE1));
		Disies d3 = disiesService.add(new Disies("Upala krajnika", Sets.newHashSet(s2, s8, s3, s9, s7, s10, s11, s12), null, Disies.Type.TYPE1));
		Symptom s28 = symptomService.add(new Symptom("Bolovao od groznice ili prehlade u poslednjih 60 dana", -5184000000L, null, null, Sets.newHashSet(d1, d2), null));
		Disies d4 = disiesService.add(new Disies("Sinusna infekcija", Sets.newHashSet(s25, s3, s12, s2, s6, s5), Sets.newHashSet(s28), Disies.Type.TYPE1));
		
		Disies d5 = disiesService.add(new Disies("Hipertenzija", Sets.newHashSet(s15), null, Disies.Type.TYPE2));
		Disies d6 = disiesService.add(new Disies("Dijabetes", Sets.newHashSet(s16, s26, s17, s18), null, Disies.Type.TYPE2));
		
		
		Symptom s29 = symptomService.add(new Symptom("Pacijent boluje od hipertenzije više od 6 meseci", 15778476000L, null, null, Sets.newHashSet(d5), null));
		Symptom s30 = symptomService.add(new Symptom("Pacijent boluje od dijabetesa", null, null, null, Sets.newHashSet(d6), null));
		Disies d7 = disiesService.add(new Disies("Hronicna bubrezna bolest", Sets.newHashSet(s17, s19, s20, s21, s23), Sets.newHashSet(s29, s30), Disies.Type.TYPE3));
		Symptom s31 = symptomService.add(new Symptom("U poslednjih 21 dana dijagnostikovana bolest za koju je primao antibiotike", -1814400000L, null, Sets.newHashSet(dr1), null, null));
		Disies d8 = disiesService.add(new Disies("Akutna bubrezna povreda", Sets.newHashSet(s24, s17, s21, s20, s22), Sets.newHashSet(s27, s31), Disies.Type.TYPE3));
		
		MedicalRecord mr1 = new MedicalRecord("0504995800050", "Borislav", "Puzic", MedicalRecord.Gender.MALE);
		mr1.setAlergicToDrugs(Sets.newHashSet(dr1));
		mr1.setAlergicToSubstances(Sets.newHashSet(sub2));
		Diagnose dg1 = diagnoseService.add(new Diagnose(d3, Sets.newHashSet(dr3), user2));
		Diagnose dg2 = diagnoseService.add(new Diagnose(d3, Sets.newHashSet(dr3), user2));
		Diagnose dg3 = diagnoseService.add(new Diagnose(d3, Sets.newHashSet(dr3), user2));
		mr1.getDiagnoses().add(dg1);
		mr1.getDiagnoses().add(dg2);
		mr1.getDiagnoses().add(dg3);
		mr1 = medicalRecordService.add(mr1);
		
		MedicalRecord mr2 = new MedicalRecord("0504995800051", "Borislava", "Puzic", MedicalRecord.Gender.FEMALE);
		mr2.setAlergicToDrugs(Sets.newHashSet(dr1));
		mr2.setAlergicToSubstances(Sets.newHashSet(sub2));
		Diagnose dg4 = diagnoseService.add(new Diagnose(d4, Sets.newHashSet(dr2), user2));
		Diagnose dg5 = diagnoseService.add(new Diagnose(d4, Sets.newHashSet(dr2), user2));
		Diagnose dg6 = diagnoseService.add(new Diagnose(d4, Sets.newHashSet(dr2), user2));
		Diagnose dg7 = diagnoseService.add(new Diagnose(d3, Sets.newHashSet(dr2), user2));
		Diagnose dg8 = diagnoseService.add(new Diagnose(d3, Sets.newHashSet(dr2), user2));
		Diagnose dg9 = diagnoseService.add(new Diagnose(d3, Sets.newHashSet(dr2), user2));
		mr2.getDiagnoses().add(dg4);
		mr2.getDiagnoses().add(dg5);
		mr2.getDiagnoses().add(dg6);
		mr2.getDiagnoses().add(dg7);
		mr2.getDiagnoses().add(dg8);
		mr2.getDiagnoses().add(dg9);
		mr2 = medicalRecordService.add(mr2);
		
		MedicalRecord mr3 = new MedicalRecord("0505995800053", "Pera", "Peric", MedicalRecord.Gender.MALE);
		mr3.setAlergicToDrugs(Sets.newHashSet(dr1));
		mr3.setAlergicToSubstances(Sets.newHashSet(sub2));
		Diagnose dg10 = diagnoseService.add(new Diagnose(d4, Sets.newHashSet(dr1), user2));
		Diagnose dg11 = diagnoseService.add(new Diagnose(d4, Sets.newHashSet(dr1), user2));
		Diagnose dg12 = diagnoseService.add(new Diagnose(d4, Sets.newHashSet(dr1), user2));
		Diagnose dg13 = diagnoseService.add(new Diagnose(d3, Sets.newHashSet(dr1), user2));
		Diagnose dg14 = diagnoseService.add(new Diagnose(d3, Sets.newHashSet(dr1), user2));
		Diagnose dg15 = diagnoseService.add(new Diagnose(d3, Sets.newHashSet(dr2), user2));
		mr3.getDiagnoses().add(dg10);
		mr3.getDiagnoses().add(dg11);
		mr3.getDiagnoses().add(dg12);
		mr3.getDiagnoses().add(dg13);
		mr3.getDiagnoses().add(dg14);
		mr3.getDiagnoses().add(dg15);
		mr3 = medicalRecordService.add(mr3);

	
//		resonerService.validateDiagnose(new DiagnoseDTO(d1.getId(), Sets.newHashSet(dr1.getId(), dr2.getId()), user2.getId()), mr1.getJmbg());
		
		
//		DisiesSymptomsFact fact1 = new DisiesSymptomsFact(d1, Sets.newHashSet(s1, s2, s3, s4));
		//resonerService.diagnose(Sets.newHashSet(s1, s2, s3, s4), Disies.Type.TYPE1, mr1.getJmbg());
		
//		KieSession kieSession = kieContainer.newKieSession();
//		HashedMap<Long, Set<Symptom>> result = new HashedMap<>();
//		List<DisiesSymptomsFact> ret = new ArrayList<>();
//		kieSession.setGlobal("disiesSymptoms", result);
//		kieSession.setGlobal("medicalRecordService", medicalRecordService);
//		kieSession.setGlobal("jmbg", mr1.getJmbg());
//		kieSession.setGlobal("ret", ret);
//		kieSession.getAgenda().getAgendaGroup("getDiagnose_1").setFocus();
//		kieSession.insert(fact1);
//		kieSession.fireAllRules();
//        kieSession.dispose();
	}
	
}
