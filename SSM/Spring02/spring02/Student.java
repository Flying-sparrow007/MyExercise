package com.gk.spring02;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Student {
	private String name;
	private Address address;
	private String[] books;
	private List<String> bys;
	private Map<String, String> card;
	private Set<String> games;
	private String lovePerson;
	private Properties info;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String[] getBooks() {
		return books;
	}

	public void setBooks(String[] books) {
		this.books = books;
	}

	public List<String> getBys() {
		return bys;
	}

	public void setBys(List<String> bys) {
		this.bys = bys;
	}

	public Map<String, String> getCard() {
		return card;
	}

	public void setCard(Map<String, String> card) {
		this.card = card;
	}

	public Set<String> getGames() {
		return games;
	}

	public void setGames(Set<String> games) {
		this.games = games;
	}

	public String getLovePerson() {
		return lovePerson;
	}

	public void setLovePerson(String lovePerson) {
		this.lovePerson = lovePerson;
	}

	public Properties getInfo() {
		return info;
	}

	public void setInfo(Properties info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", address=" + address + ", books=" + Arrays.toString(books) + ", bys=" + bys
				+ ", card=" + card + ", games=" + games + ", lovePerson=" + lovePerson + ", info=" + info + "]";
	}
	
}
