package com.blz.iplprogram;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

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

	@Test
	public void givenIPLInfo_ShouldReturnTopStrikeRatings() {
		String sortedCensusData = null;
		try {
			sortedCensusData = iplAnalyser.getTopStrikingRates(FILE_PATH);
			IPLMostRunsCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IPLMostRunsCSV[].class);
			Assert.assertNotEquals(333.33, censusCSV[censusCSV.length - 1].avg, DELTA);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLInfo_ShouldReturnTop4sStrikerCricketer() throws IOException, CensusAnalyserException {
		List<IPLMostRunsCSV> lst = new IPLAnalyser().getTop6sStrikerCricketer(FILE_PATH);
		Assert.assertEquals("Shikhar Dhawan", new IPLAnalyser().getTop4sStrikerCricketer(FILE_PATH).get(0).player);
	}
	
	@Test
	public void givenIPLInfo_ShouldReturnTop6sStrikerCricketer() throws IOException, CensusAnalyserException {
		List<IPLMostRunsCSV> lst = new IPLAnalyser().getTop6sStrikerCricketer(FILE_PATH);
		Assert.assertEquals("Andre Russell", new IPLAnalyser().getTop6sStrikerCricketer(FILE_PATH).get(0).player);
	}
}
