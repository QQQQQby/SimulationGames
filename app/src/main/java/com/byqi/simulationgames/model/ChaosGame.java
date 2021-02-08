package com.byqi.simulationgames.model;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class ChaosGame {

    Pair<Double, Double> P;
    List<Pair<Integer, Integer>> polygon;
    HashSet<Pair<Integer, Integer>> history;
    Random random;

    public ChaosGame() {
        this(null);
    }

    public ChaosGame(Pair<Double, Double> P) {
        this.P = P;
        this.polygon = new ArrayList<>();
        history = new HashSet<>();
        random = new Random(System.currentTimeMillis());
    }

    public void setP(Pair<Double, Double> P) {
        this.P = P;
    }

    public Pair<Double, Double> getP() {
        return P;
    }

    public void addPointToPolygon(Pair<Integer, Integer> point) {
        if (point != null)
            polygon.add(point);
    }

    public List<Pair<Integer, Integer>> getPolygon() {
        return polygon;
    }

    public HashSet<Pair<Integer, Integer>> getHistory() {
        return history;
    }

    public void updateP() {
        if (P == null)
            return;
        history.add(new Pair<>((int) (Math.ceil(P.first)), (int) (Math.ceil(P.second))));
        int index = random.nextInt(polygon.size());
        Pair<Integer, Integer> candidate = polygon.get(index);
        P = new Pair<>((candidate.first + P.first) / 2.0, (candidate.second + P.second) / 2.0);
    }
}
