package edu.northeastern.info6205.tspsolver.algorithm.opt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.northeastern.info6205.tspsolver.model.Point;
import edu.northeastern.info6205.tspsolver.util.PointUtil;

public class TwoOpt {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwoOpt.class);

    private List<Point> tour;

    /**
        Strategy 1 - Choose two random vertices for swap
        Strategy 2 - Choose two vertices that are adjacent to each other
        Strategy 3 - Iterative combination of one and two
     */
    private int strategy;

    //Computation power budget
    private long budget;

    public TwoOpt(List<Point> tour, int strategy, long budget) {
    	LOGGER.info(
    			"initialising with tour size: {}, strategy: {}, budget: {}",
    			tour.size(),
    			strategy,
    			budget);
    	
        this.tour = tour;
        this.strategy = strategy;
        this.budget = budget;
    }

    public void improve() {
        if (strategy == 1) {
            strategy1();
        } else if (strategy == 2) {
            strategy2();
        } else if (strategy == 3) {
            strategy3();
        } 
    }

    public List<Point> getImprovedTour() {
        return tour;
    }

    private boolean swapNodes(int i, int j) {
        //current tour distance
        double cost = PointUtil.getTotalCost(tour);

        //duplicate the tour
        List<Point> improvedTour = new ArrayList<>(tour);

        //swap the nodes
        Collections.swap(improvedTour, i, j);

        //new tour distance
        double newCost = PointUtil.getTotalCost(improvedTour);

        //update the tour if new cost is less than old cost
        if(newCost < cost) {
            LOGGER.trace("Improvement found before swap: {}, after swap: {}", cost, newCost);
            this.tour = improvedTour;
            return true;
        }
        return false;
    }

    private void strategy1() {
        // Choose two random vertices for swap
        boolean improvement = true;
        while(improvement || budget > 0) {
            improvement = false;
            List<Integer> randomNumbers;
            Random random = new Random();
            do {
                randomNumbers = random.ints(2, 1, tour.size() - 1)
                        .boxed()
                        .collect(Collectors.toList());
            } while (randomNumbers.get(0) == randomNumbers.get(1));

            int randomEdge_1 = randomNumbers.get(0);
            int randomEdge_2 = randomNumbers.get(1);

            improvement = swapNodes(randomEdge_1, randomEdge_2);
            budget--;
            
            LOGGER.trace("Budget remaining : {}", budget);
        }
    }

    private void strategy2() {
        // Choose two vertices that are adjacent to each other
        boolean improvement = true;
        while(improvement) {
            improvement = false;
            for(int i = 1; i < tour.size() - 1; i++) {
                improvement = swapNodes(i, i + 1);
                if(improvement) {
                    break;
                }
            }
        }

    }

    private void strategy3() {
        //Iterative combination of one and two
        strategy1();
        strategy2();
    }
}
