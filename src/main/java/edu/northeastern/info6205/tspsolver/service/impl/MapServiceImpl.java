package edu.northeastern.info6205.tspsolver.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.northeastern.info6205.tspsolver.model.Action;
import edu.northeastern.info6205.tspsolver.model.ActionType;
import edu.northeastern.info6205.tspsolver.model.Edge;
import edu.northeastern.info6205.tspsolver.model.Point;
import edu.northeastern.info6205.tspsolver.service.MapService;
import edu.northeastern.info6205.tspsolver.service.WebSocketPublishService;

public class MapServiceImpl implements MapService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MapServiceImpl.class);

	private WebSocketPublishService webSocketPublishService;

	private static MapService instance;
	
	private MapServiceImpl() {
		LOGGER.info("Initialising the instance");
		webSocketPublishService = WebSocketPublishServiceImpl.getInstance();
	}
	
	public static MapService getInstance() {
		if (instance == null) {
			instance = new MapServiceImpl();
		}
		
		return instance;
	}
	
	@Override
	public void publishClearMap() {
//		LOGGER.trace("publishing clear map message");
		Action<Void> action = new Action<>();
		action.setActionType(ActionType.CLEAR_MAP);
		webSocketPublishService.publish(action);
	}
	
	@Override
	public void publishAddStartPointMarker(Point point) {
//		LOGGER.trace("publishing add start point marker for : {}", point);
		Action<Point> action = new Action<>();
		action.setActionType(ActionType.ADD_START_POINT);
		action.setPayload(point);
		webSocketPublishService.publish(action);
	}

	@Override
	public void publishAddPointsAndFitBound(List<Point> points) {
//		LOGGER.trace("publishing add points and fit bounds for points size: {}", points.size());
		
		Action<List<Point>> action = new Action<>();
		action.setActionType(ActionType.ADD_POINT_LIST_AND_FIT_BOUND);
		action.setPayload(points);
		
		webSocketPublishService.publish(action);
	}
	
	@Override
	public void publishAddMSTPolylineAndFitBound(List<Edge> edges) {
//		LOGGER.trace("publishing add mst polyline and fit bounds for points size: {}", points.size());

		Action<List<Edge>> action = new Action<>();
		action.setActionType(ActionType.ADD_MST_POLYLINE_AND_FIT_BOUND);
		action.setPayload(edges);
		
		webSocketPublishService.publish(action);
	}
	
	@Override
	public void publishClearMSTPolyline() {
//		LOGGER.trace("publishing MST Polyline message");
		Action<Void> action = new Action<>();
		action.setActionType(ActionType.CLEAR_MST_POLYLINE);
		webSocketPublishService.publish(action);
	}
	
	@Override
	public void publishAddPolylineAndFitBound(List<Point> points) {
//		LOGGER.trace("publishing add polyline and fit bounds for points size: {}", points.size());
		
		Action<List<Point>> action = new Action<>();
		action.setActionType(ActionType.ADD_POLYLINE_AND_FIT_BOUND);
		action.setPayload(points);
		
		webSocketPublishService.publish(action);
	}

	@Override
	public void publishPointRelaxed(String id) {
//		LOGGER.trace("publishing point relax message for id: {}", id);
		
		Action<String> action = new Action<>();
		action.setActionType(ActionType.POINT_RELAXED);
		action.setPayload(id);
		
		webSocketPublishService.publish(action);
	}

	@Override
	public void publishDrawEdge(Edge edge) {
//		LOGGER.trace("publishing draw edge message");
		
		Action<Edge> action = new Action<>();
		action.setActionType(ActionType.DRAW_EDGE);
		action.setPayload(edge);
		
		webSocketPublishService.publish(action);
	}

	@Override
	public void publishChangePointColorRed(String id) {
//		LOGGER.trace("publishing point color change red for id: {}", id);
		
		Action<String> action = new Action<>();
		action.setActionType(ActionType.CHANGE_POINT_COLOR_RED);
		action.setPayload(id);
		
		webSocketPublishService.publish(action);
		
	}

	@Override
	public void publishChangePointColorGreen(String id) {
//		LOGGER.trace("publishing point color change green for id: {}", id);
		
		Action<String> action = new Action<>();
		action.setActionType(ActionType.CHANGE_POINT_COLOR_GREEN);
		action.setPayload(id);
		
		webSocketPublishService.publish(action);
	}

	@Override
	public void publishAddGreenLine(Edge edge) {
//		LOGGER.trace("publishing add green line message");
		
		Action<Edge> action = new Action<>();
		action.setActionType(ActionType.DRAW_EDGE_COLOR_GREEN);
		action.setPayload(edge);
		
		webSocketPublishService.publish(action);
	}

	@Override
	public void publishOutput(String information) {
//		LOGGER.trace("publishing output information: {}", information);
		
		Action<String> action = new Action<>();
		action.setActionType(ActionType.SHOW_OUTPUT_INFORMATION);
		action.setPayload(information);
		
		webSocketPublishService.publish(action);
	}

}
