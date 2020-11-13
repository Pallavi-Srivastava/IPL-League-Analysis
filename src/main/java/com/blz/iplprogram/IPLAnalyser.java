package com.blz.iplprogram;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class IPLAnalyser {

	public static List<IPLMostRunsCSV> iplCSVList;

	public int loadCSVData(String indiaCensusCSVFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCSVFilePath))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			iplCSVList = csvBuilder.getCSVFileList(reader, IPLMostRunsCSV.class);
			return iplCSVList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
		}
	}

	public String getTopBattingAverages(String csvFilePath) throws CensusAnalyserException {
		loadCSVData(csvFilePath);
		if (iplCSVList == null || iplCSVList.size() == 0) {
			throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
		}
		Comparator<IPLMostRunsCSV> censusComparator = Comparator.comparing(census -> census.avg);
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(this.iplCSVList);
		return sortedStateCensusJson;
	}

	public String getTopStrikingRates(String csvFilePath) throws CensusAnalyserException {
		loadCSVData(csvFilePath);
		if (iplCSVList == null || iplCSVList.size() == 0) {
			throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
		}
		Comparator<IPLMostRunsCSV> censusComparator = Comparator.comparing(census -> census.sr);
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(this.iplCSVList);
		return sortedStateCensusJson;
	}

	public List<IPLMostRunsCSV> getTop4sStrikerCricketer(String csvFilePath)
			throws IOException, CensusAnalyserException {
		loadCSVData(csvFilePath);
		List<IPLMostRunsCSV> playerWithMax4s = iplCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getNum4s(), player2.getNum4s()))
				.collect(Collectors.toList());
		Collections.reverse(playerWithMax4s);
		return playerWithMax4s;
	}
	
	public List<IPLMostRunsCSV> getTop6sStrikerCricketer(String csvFilePath)
			throws IOException, CensusAnalyserException {
		loadCSVData(csvFilePath);
		List<IPLMostRunsCSV> playerWithMax4s = iplCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getNum6s(), player2.getNum6s()))
				.collect(Collectors.toList());
		Collections.reverse(playerWithMax4s);
		return playerWithMax4s;
	}

	public void sort(Comparator<IPLMostRunsCSV> censusComparator) {
		for (int i = 0; i < iplCSVList.size(); i++) {
			for (int j = 0; j < iplCSVList.size() - i - 1; j++) {
				IPLMostRunsCSV census1 = iplCSVList.get(j);
				IPLMostRunsCSV census2 = iplCSVList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					iplCSVList.set(j, census2);
					iplCSVList.set(j + 1, census1);
				}
			}
		}
	}
}