package br.com.studiotrek.impactaservice.base.regra;

public interface IRegra<T> {
    T parseHtml(String cookie) throws Exception;
}
