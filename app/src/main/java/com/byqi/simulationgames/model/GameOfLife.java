package com.byqi.simulationgames.model;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GameOfLife {

    private int row, col;
    private HashSet<Pair<Integer, Integer>> cells;

    private final int[][] neighbourOffsets = new int[][]{
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
    };

    public GameOfLife(int row, int col) {
        this.row = row;
        this.col = col;
        cells = new HashSet<>();
    }

    public List<Pair<Integer, Integer>> getCells() {
        return new ArrayList<>(cells);
    }

    public void addCell(Pair<Integer, Integer> cell) {
        if (cell.first >= 0 && cell.first < row && cell.second >= 0 && cell.second < col)
            cells.add(cell);
    }

    public void updateCells() {
        synchronized (cells) {
            HashSet<Pair<Integer, Integer>> nextStateCells = new HashSet<>();
            HashMap<Pair<Integer, Integer>, Integer> numNeighbours = getNumberNeighbours();
            for (HashMap.Entry<Pair<Integer, Integer>, Integer> numNeighboursEntry : numNeighbours.entrySet()) {
                Pair<Integer, Integer> neighbour = numNeighboursEntry.getKey();
                int count = numNeighboursEntry.getValue();
                if (count == 2) { // The cell has 2 neighbours
                    if (cells.contains(neighbour)) // alive
                        nextStateCells.add(neighbour);
                } else if (count == 3) { // The cell has 3 neighbours, whether it is dead or alive
                    nextStateCells.add(neighbour);
                }
            }
            cells = nextStateCells;
        }
    }

    public void clearCells() {
        synchronized (cells) {
            cells = new HashSet<>();
        }
    }

    private HashMap<Pair<Integer, Integer>, Integer> getNumberNeighbours() {
        HashMap<Pair<Integer, Integer>, Integer> neighbours = new HashMap<>();
        for (Pair<Integer, Integer> cell : cells) {
            for (int[] offset : neighbourOffsets) {
                Pair<Integer, Integer> pair = new Pair<>(cell.first + offset[0], cell.second + offset[1]);
                neighbours.put(pair, neighbours.getOrDefault(pair, 0) + 1);
            }
        }
        for (int i = -1; i <= row; i++) {
            neighbours.remove(new Pair<>(i, -1));
            neighbours.remove(new Pair<>(i, col));
        }
        for (int j = 0; j < col; j++) {
            neighbours.remove(new Pair<>(-1, j));
            neighbours.remove(new Pair<>(row, j));
        }
        return neighbours;
    }

}
