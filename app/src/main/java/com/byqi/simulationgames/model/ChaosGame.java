package com.byqi.simulationgames.model;

import android.util.Pair;

import java.util.HashSet;
import java.util.Random;

public class ChaosGame {

    Pair<Integer, Integer> A;
    Pair<Integer, Integer> B;
    Pair<Integer, Integer> C;
    Pair<Double, Double> P;
    HashSet<Pair<Integer, Integer>> history;
    Random random;

    public ChaosGame() {
        this(null, null, null);
    }

    public ChaosGame(Pair<Integer, Integer> A, Pair<Integer, Integer> B, Pair<Integer, Integer> C) {
        this(A, B, C, null);
    }

    public ChaosGame(Pair<Integer, Integer> A, Pair<Integer, Integer> B, Pair<Integer, Integer> C, Pair<Double, Double> P) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.P = P;
        history = new HashSet<>();
        random = new Random(System.currentTimeMillis());
    }

    public void setA(Pair<Integer, Integer> A) {
        this.A = A;
    }

    public void setB(Pair<Integer, Integer> B) {
        this.B = B;
    }

    public void setC(Pair<Integer, Integer> C) {
        this.C = C;
    }

    public void setP(Pair<Double, Double> P) {
        this.P = P;
    }

    public Pair<Integer, Integer> getA() {
        return A;
    }

    public Pair<Integer, Integer> getB() {
        return B;
    }

    public Pair<Integer, Integer> getC() {
        return C;
    }

    public Pair<Double, Double> getP() {
        return P;
    }

    public HashSet<Pair<Integer, Integer>> getHistory() {
        return history;
    }

    public void updateP() {
        if (A == null || B == null || C == null || P == null)
            return;
        history.add(new Pair<>((int)(Math.ceil(P.first)), (int)(Math.ceil(P.second))));
        double chance = random.nextDouble();
        Pair<Integer, Integer> candidate;
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
