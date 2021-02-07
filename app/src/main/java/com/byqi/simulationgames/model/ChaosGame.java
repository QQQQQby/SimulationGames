package com.byqi.simulationgames.model;

import android.util.Pair;

import java.util.HashSet;
import java.util.Random;

class ChaosGame {

    Pair<Double, Double> A, B, C, P;
    HashSet<Pair<Integer, Integer>> history;
    Random random;

    public ChaosGame(Pair<Double, Double> A, Pair<Double, Double> B, Pair<Double, Double> C) {
        this(A, B, C, null);
    }

    public ChaosGame(Pair<Double, Double> A, Pair<Double, Double> B, Pair<Double, Double> C, Pair<Double, Double> P) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.P = P;
        history = new HashSet<>();
        Random random = new Random(System.currentTimeMillis());
    }

    public Pair<Double, Double> getA() {
        return A;
    }

    public Pair<Double, Double> getB() {
        return B;
    }

    public Pair<Double, Double> getC() {
        return C;
    }

    public Pair<Double, Double> getP() {
        return P;
    }

    public HashSet<Pair<Integer, Integer>> getHistory() {
        return history;
    }

    public void updateP() {
        history.add(new Pair<>(P.first.intValue(), P.second.intValue()));
        double chance = random.nextDouble();
        Pair<Double, Double> candidate;
        if (chance < 1 / 3.0) {
            candidate = A;
        } else if (chance < 2 / 3.0) {
            candidate = B;
        } else {
            candidate = C;
        }
        P = new Pair<>((candidate.first + P.first) / 2.0, (candidate.second + P.second) / 2.0);
    }
}
