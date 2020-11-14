package com.blz.iplprogram;

import java.io.IOException;
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
		Assert.assertEquals("Shikhar Dhawan", new IPLAnalyser().getTop4sStrikerCricketer(FILE_PATH).get(0).player);
	}

	@Test
	public void givenIPLInfo_ShouldReturnTop6sStrikerCricketer() throws IOException, CensusAnalyserException {
		Assert.assertEquals("Andre Russell", new IPLAnalyser().getTop6sStrikerCricketer(FILE_PATH).get(0).player);
	}

	@Test
	public void givenIPLInfo_ShouldReturnTop6sStrikerNAveragesCricketer() throws IOException, CensusAnalyserException {
		Assert.assertEquals("Andre Russell",
				new IPLAnalyser().bestStrikingRatesWith6sN4sCriketer(FILE_PATH).get(0).player);
	}

	@Test
	public void givenIPLInfo_ShouldReturnbestStrikingRatesWithBestAverages()
			throws IOException, CensusAnalyserException {
		Assert.assertEquals("MS Dhoni", new IPLAnalyser().bestStrikingRatesWithBestAverages(FILE_PATH).get(0).player);
	}

	@Test
	public void givenIPLInfo_ShouldReturnBestCricketersWhoHitMaxWithBestAverages()
			throws IOException, CensusAnalyserException {
		Assert.assertEquals("David Warner",
				new IPLAnalyser().bestCricketersWhoHitMaxWithBestAverages(FILE_PATH).get(0).player);
	}
}
