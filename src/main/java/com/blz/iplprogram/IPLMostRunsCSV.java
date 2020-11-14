package com.blz.iplprogram;

import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV {

	@Override
	public String toString() {
		return "IPLMostRunsCSV [pos=" + pos + ", player=" + player + ", mat=" + mat + ", inns=" + inns + ", no=" + no
				+ ", runs=" + runs + ", hs=" + hs + ", avg=" + avg + ", bf=" + bf + ", sr=" + sr + ", number100="
				+ number100 + ", number50=" + number50 + ", number4s=" + number4s + ", number6s=" + number6s + "]";
	}

	@CsvBindByName(column = "POS")
	public int pos;

	@CsvBindByName(column = "PLAYER")
	public String player;

	@CsvBindByName(column = "Mat")
	public int mat;

	@CsvBindByName(column = "Inns")
	public int inns;

	@CsvBindByName(column = "NO")
	public int no;

	@CsvBindByName(column = "Runs")
	public int runs;

	@CsvBindByName(column = "HS")
	public int hs;

	@CsvBindByName(column = "Avg")
	public double avg;

	@CsvBindByName(column = "BF")
	public int bf;

	@CsvBindByName(column = "SR")
	public double sr;

	@CsvBindByName(column = "100")
	int number100;

	@CsvBindByName(column = "50")
	public int number50;

	@CsvBindByName(column = "4s")
	public int number4s;

	@CsvBindByName(column = "6s")
	public int number6s;

	public int getNum4s() {
		return number4s;
	}

	public int getNum6s() {
		return number6s;
	}

	public double getSr() {
		return sr;
	}

	public double getAvg() {
		return avg;
	}

	public int getRuns() {
		return runs;
	}

	public int getCentury() {
		return number100;
	}
}
