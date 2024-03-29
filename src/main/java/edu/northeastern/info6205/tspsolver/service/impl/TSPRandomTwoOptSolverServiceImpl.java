package edu.northeastern.info6205.tspsolver.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.northeastern.info6205.tspsolver.algorithm.christofides.Christofides;
import edu.northeastern.info6205.tspsolver.algorithm.opt.TwoOpt;
import edu.northeastern.info6205.tspsolver.constant.Constant;
import edu.northeastern.info6205.tspsolver.model.Point;
import edu.northeastern.info6205.tspsolver.model.TSPPayload;
import edu.northeastern.info6205.tspsolver.model.TSPPayload.TwoOptPayload;
import edu.northeastern.info6205.tspsolver.service.TSPSolverService;

public class TSPRandomTwoOptSolverServiceImpl implements TSPSolverService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TSPRandomTwoOptSolverServiceImpl.class);
	
	private static TSPSolverService instance;
	
	private TSPRandomTwoOptSolverServiceImpl() {
		LOGGER.info("Initialising the instance");
	}
	
	public static TSPSolverService getInstance() {
		if (instance == null) {
			instance = new TSPRandomTwoOptSolverServiceImpl();
		}
		
		return instance;
	}
	
	@Override
	public String getKeyIdentifier() {
		return Constant.KEY_IDENTIFIER_RANDOM_TWO_OPT;
	}

	@Override
	public String getName() {
		return Constant.NAME_RANDOM_TWO_OPT;
	}

	@Override
	public List<Point> solve(
			List<Point> points, 
			int startingPointIndex, 
			TSPPayload payload) {
		LOGGER.info(
				"Random Two Opt will solve for points size: {}, startingPointIndex: {}, payload: {}", 
				points.size(),
				startingPointIndex,
				payload);
		
		Christofides christofides = new Christofides(points);
		List<Point> tour = christofides.solve();
		
		TwoOptPayload twoOptPayload = payload.getTwoOptPayload();
		TwoOpt twoOpt = new TwoOpt(tour, twoOptPayload.getStrategy(), twoOptPayload.getBudget());
		twoOpt.improve();
		List<Point> improvedTour = twoOpt.getImprovedTour();
		return improvedTour;
	}
	
}
