package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    AStarGraph<Vertex> input;
    Vertex start;
    Vertex end;
    double timeout;
    int numStatesExplored;
    double explorationTime;
    DoubleMapPQ<Vertex> solverPQ;
    HashMap<Vertex, Double> DistTo;

    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        this.input = input;
        this.start = start;
        this.end = end;
        this.timeout = timeout;
        numStatesExplored = 0;
        explorationTime = 0.0;
        solverPQ = new DoubleMapPQ<>();
        DistTo = new HashMap<>();

        solution = solution();
        outcome = outcome();
        solutionWeight = solutionWeight();
        timeSpent = explorationTime();
    }

    public SolverOutcome outcome(){
        if (explorationTime > timeout){
            return SolverOutcome.TIMEOUT;
        }
        else if(solution.size() == 0) {
            return SolverOutcome.UNSOLVABLE;
        }
        else {
            return SolverOutcome.SOLVED;
        }
    }
    public List<Vertex> solution(){
        solverPQ = new DoubleMapPQ<>();
        DistTo = new HashMap<>();
        List<Vertex> paths = new ArrayList<>();
        HashMap<Vertex, Vertex> pathfrom = new HashMap<>();
        solverPQ.add(start, input.estimatedDistanceToGoal(start, end));
        DistTo.put(start, 0.0);
        Stopwatch sw = new Stopwatch();
        while(solverPQ.size()>0 && solverPQ.getSmallest() != end && explorationTime < timeout) {
            Vertex curV = solverPQ.removeSmallest();
            paths.add(curV);
            for(WeightedEdge<Vertex> neiE: input.neighbors(curV)){
                Vertex neiV = neiE.to();
                if (paths.contains(neiV)) {
                    continue;
                }
                double w = neiE.weight();
                double distTop = DistTo.get(curV);
                System.out.println(curV.toString());
                //System.out.println(distTop);

                if ((DistTo.containsKey(neiV) && DistTo.get(neiV) > DistTo.get(curV) + w) || !DistTo.containsKey(neiV)){
                    DistTo.put(neiV, DistTo.get(curV) + w);
                    pathfrom.put(neiV, curV);
                    double ptoEnd = input.estimatedDistanceToGoal(curV, end);
                    double qtoEnd = input.estimatedDistanceToGoal(neiV, end);

                    if (solverPQ.contains(neiV)){
                        solverPQ.changePriority(neiV, DistTo.get(neiV) + qtoEnd);
                    }
                    else{
                        solverPQ.add(neiV, DistTo.get(neiV) + qtoEnd);
                    }
                }
            }
            explorationTime = sw.elapsedTime();
            numStatesExplored += 1;
        }

        paths = new ArrayList<>();

        if (explorationTime > timeout){
            return new ArrayList<>();
        }
        else if(solverPQ.size() == 0 && !DistTo.containsKey(end)) {
            return new ArrayList<>();
        }
        else {
            Vertex trackv = end;
            while (trackv != start) {
                paths.add(0, trackv);
                trackv = pathfrom.get(trackv);
            }
            paths.add(0,start);
            return paths;
        }

    }
    public double solutionWeight(){
        if (solution == null || solution.size() == 0) {return 0;}
        //System.out.println(solution);
        //System.out.println(DistTo);
        return DistTo.get(end);
    }
    public int numStatesExplored(){
        return numStatesExplored;
    }
    public double explorationTime(){
        return explorationTime;
    }
}
