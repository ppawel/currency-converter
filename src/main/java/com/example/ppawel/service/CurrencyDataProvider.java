package com.example.ppawel.service;

/**
 * API for currency data provider. Implementations are assumed to interface with
 * external data sources although this interface can also be used for mocking
 * purposes.
 * <p>
 * Implementations can cache results if calls to the external data source are
 * expensive or limited.
 * 
 * @author ppawel
 *
 */
public interface CurrencyDataProvider extends CurrencyOperations {

}
