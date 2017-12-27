package com.yao.lib_common.sign;

public class Data {

	/**
	 * MD5 SHA-256 UTF-8 appTimestamp appKey sign android_lk98f83
	 * flvjk342589fdgjl34m9sdufg234oiy
	 */
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;
	private String h;

	public String a() {
		return this.a;//MD5
	}

	public void a(String paramString) {
		this.a = paramString;
	}

	public String b() {
		return this.b;//SHA-256
	}

	public void b(String paramString) {
		this.b = paramString;
	}

	public String c() {
		return this.c;//UTF-8
	}

	public void c(String paramString) {
		this.c = paramString;
	}

	public String d() {
		return this.d;//appTimestamp
	}

	public void d(String paramString) {
		this.d = paramString;
	}

	public String e() {
		return System.currentTimeMillis() + "";
	}

	public void e(String paramString) {
		this.e = paramString;
	}

	public String f() {
		return this.e;//appKey
	}

	public void f(String paramString) {
		this.f = paramString;
	}

	public String g() {
		return this.f;//sign
	}

	public void g(String paramString) {
		this.g = paramString;
	}

	public String h() {
		return this.g;//android_lk98f83
	}

	public void h(String paramString) {
		this.h = paramString;
	}

	public String i() {
		return this.h;//flvjk342589fdgjl34m9sdufg234oiy
	}

}
