package com.in28minutes.rest.services.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningControl {
	@GetMapping("person/v1")
	public Personv1 personv1() {
		return new Personv1("Edge");}
	@GetMapping("person/v2")
	public Personv2 personv2() {
		return new Personv2(new Name("Kushik","Paul"));}
	@GetMapping(value="person/param",params="v=1")
	public Personv1 paramv1() {
		return new Personv1("Edge");}
	@GetMapping(value="person/param",params="v=2")
	public Personv2 param2() {
		return new Personv2(new Name("Kushik","Paul"));}
	@GetMapping(value="person/header",headers="V=1")
	public Personv1 headerv1() {
		return new Personv1("Edge");}
	@GetMapping(value="person/header",headers="V=2")
	public Personv2 headerv2() {
		return new Personv2(new Name("Kushik","Paul"));}
	@GetMapping(value="person/produces", produces="application/vnd.company.app-v1+json")
	public Personv1 producesv1() {
		return new Personv1("Edge");}
	@GetMapping(value="person/produces",produces="application/vnd.company.app-v2+json")
	public Personv2 producesv2() {
		return new Personv2(new Name("Kushik","Paul"));}

}
