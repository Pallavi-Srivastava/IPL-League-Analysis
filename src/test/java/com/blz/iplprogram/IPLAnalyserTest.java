package com.blz.iplprogram;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.google.gson.Gson;

public class IPLAnalyserTest {

	public static final String FILE_PATH = "E:\\Fellowship\\STS\\CensusAnalyserFile\\CorrectRuns.csv";
	private static final double DELTA = 1e-15;

	private static IPLAnalyser iplAnalyser;

	@BeforeClass
	public static void createcensusAnalyser() {
		iplAnalyser = new IPLAnalyser();
		System.out.println("Welcome to the IPL League Analyser Program.. ");
	}

	@Test
	public void givenIPLInfo_ShouldReturnNumberOfRecords() throws CensusAnalyserException {
		Assert.assertEquals(101, iplAnalyser.loadCSVData(FILE_PATH));
	}

	@Test
	public void givenIPLInfo_ShouldReturnTopBattingAverages() {
		String sortedCensusData = null;
		try {
			sortedCensusData = iplAnalyser.getTopBattingAverages(FILE_PATH);
			IPLMostRunsCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IPLMostRunsCSV[].class);
			Assert.assertEquals(83.2, censusCSV[censusCSV.length - 1].avg, DELTA);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
}
