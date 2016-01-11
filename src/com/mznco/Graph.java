package com.mznco;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Misery on 1/11/16.
 */
public class Graph {
    private class Node{
        protected ArrayList<Node> children;
        protected ArrayList<Node> parents;
        protected String name;

        public Node(String name){
            children = new ArrayList<Node>();
            parents = new ArrayList<Node>();
            this.name = name;
        }
    }

    public HashMap<String, Node> allNodes;

    public Graph(){
        allNodes = new HashMap<String, Node>();
    }


    public void addNode(String name){
        Node newNode = new Node(name);
        allNodes.put(name, newNode);
    }

    public void addEdge(String parentName, String ChildName){
        Node parentNode = allNodes.get(parentName);
        Node childNode = allNodes.get(ChildName);

        if(parentNode != null && childNode != null){
            parentNode.children.add(childNode);
            childNode.parents.add(parentNode);
        }
    }

    public void removeNode(String name){
        Node thisNode = allNodes.get(name);
        for(int i = 0; i < thisNode.parents.size(); i++){
            Node parent = thisNode.parents.get(i);
            parent.children.remove(thisNode);//remove all paths to this node
        }
        allNodes.remove(name);//remove this from the list of all nodes
    }

    public boolean findPath(String start, String end){
        //return BreadthFirstSearch(start, end);
        return bidirectionalSearch(start, end);
    }

    public boolean bidirectionalSearch(String start, String end){
        Node startNode = allNodes.get(start);
        Node endNode = allNodes.get(end);

        Queue<Node> startSearch = new LinkedBlockingQueue<>();
        Queue<Node> endSearch = new LinkedBlockingQueue<>();

        startSearch.add(startNode);
        endSearch.add(endNode);

        ArrayList<Node> startVisited = new ArrayList<Node>();
        ArrayList<Node> endVisited = new ArrayList<Node>();

        while(!startSearch.isEmpty() || !endSearch.isEmpty()){
            Node currentNode;
            if(!startSearch.isEmpty()){
                currentNode = startSearch.remove();
                startVisited.add(currentNode);
                if(endVisited.contains(currentNode))
                    return true;
                for(int i = 0; i < currentNode.children.size(); i++){
                    startSearch.add(currentNode.children.get(i));
                }
            }
            if(!endSearch.isEmpty()){
                currentNode = endSearch.remove();
                endVisited.add(currentNode);
                if(startVisited.contains(currentNode))
                    return true;
                for(int i = 0; i < currentNode.parents.size(); i++){
                    endSearch.add(currentNode.parents.get(i));
                }
            }
        }
        return false;
    }

    public boolean  BreadthFirstSearch(String start, String end){

        Node startNode = allNodes.get(start);
        Node endNode = allNodes.get(end);

        Queue<Node> toSearch = new LinkedBlockingQueue<>();

        toSearch.add(startNode);

        while(!toSearch.isEmpty()){
            Node currentNode = toSearch.remove();
            if(currentNode == endNode)
                return true;
            for(int i = 0; i < currentNode.children.size(); i++){
                toSearch.add(currentNode.children.get(i));
            }
        }
        return false;
    }

    public void printGraph(){
        //Set<String> keySet = allNodes.keySet();
        String[] names = new String[allNodes.size()];
        allNodes.keySet().toArray(names);
        for(int i = 0; i < names.length; i++){
            System.out.print(" " + names[i]);
        }
        System.out.println();
        for(int i = 0; i < names.length; i++){
            System.out.print(names[i]);
            Node child = allNodes.get(names[i]);
            for(int j = 0; j < names.length; j++){
                Node parent = allNodes.get(names[j]);
                if(parent.children.contains(child))
                    System.out.print("1 ");
                else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }

    }

}
