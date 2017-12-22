package demo.service;


import demo.model.PredictedPrice;

public interface HousePricePredictionService {
    PredictedPrice predict(double bedroom, double bathroom, double areaSize);
}
