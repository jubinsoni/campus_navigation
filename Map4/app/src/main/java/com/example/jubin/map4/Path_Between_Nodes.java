package com.example.jubin.map4;

/**
 * Created by jubin on 2/11/17.
 */
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Path_Between_Nodes {
    public Map<String, LinkedHashSet<String>> map = new HashMap();
    public static LinkedList<String> link_ll = new LinkedList<String>();

    public void addEdge(String node1, String node2)
    {
        LinkedHashSet<String> adjacent = map.get(node1);
        if (adjacent == null)
        {
            adjacent = new LinkedHashSet();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }

    public void addTwoWayVertex(String node1, String node2)
    {
        addEdge(node1, node2);
        addEdge(node2, node1);
    }

    public boolean isConnected(String node1, String node2)
    {
        Set adjacent = map.get(node1);
        if (adjacent == null)
        {
            return false;
        }
        return adjacent.contains(node2);
    }

    public LinkedList<String> adjacentNodes(String last)
    {
        LinkedHashSet<String> adjacent = map.get(last);
        if (adjacent == null)
        {
            return new LinkedList();
        }
        return new LinkedList<String>(adjacent);
    }

    public void breadthFirst(Path_Between_Nodes graph, LinkedList<String> visited)
    {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
        for (String node : nodes)
        {
            if (visited.contains(node))
            {
                continue;
            }
            if (node.equals(END))
            {
                visited.add(node);
                printPath(visited);
                flag = true;
                visited.removeLast();
                break;
            }
        }

        for (String node : nodes)
        {
            if (visited.contains(node) || node.equals(END))
            {
                continue;
            }
            visited.addLast(node);
            breadthFirst(graph, visited);
            visited.removeLast();
        }
        if (flag == false)
        {
            System.out.println("No path Exists between " + START + " and " + END);
            flag = true;
        }
    }

    public void printPath(LinkedList<String> visited)
    {
        if (flag == false)
        {
            System.out.println("Yes there exists a path between " + START + " and " + END);
        }
        String str = "";
        for (String node : visited)
        {
            System.out.print(node);
            System.out.print(" ");
            str = str + node + ",";
        }
        System.out.println();
        System.out.println(str);
        link_ll.add(str);
    }

    public static String START;
    public static String END;
    public static boolean flag;

    /*
    public static void main(String[] args) {
        Path_Between_Nodes graph = new Path_Between_Nodes();
        graph.addTwoWayVertex("0", "2");
        graph.addEdge("1", "0");
        graph.addTwoWayVertex("1", "3");
        graph.addTwoWayVertex("1", "4");
        graph.addEdge("1", "5");
        //graph.addEdge("2", "0");
        graph.addTwoWayVertex("2", "4");
        graph.addTwoWayVertex("2", "5");
        //graph.addEdge("3", "1");
        //graph.addEdge("4", "2");
        //graph.addEdge("4", "1");
        graph.addTwoWayVertex("4", "5");
        //graph.addEdge("5", "2");
        // graph.addEdge("5", "4");
        LinkedList<String> visited = new LinkedList();
        //System.out.println("Enter the source node:");
        START = "0";
        //System.out.println("Enter the destination node:");
        END = "5";
        visited.add(START);
        new Path_Between_Nodes().breadthFirst(graph, visited);
        System.out.println(link_ll);
        //write code for minimum length of string here
        for (int i = 0; i <= link_ll.size() - 1; i++) {
            String str_node = link_ll.get(i);
            System.out.print(str_node + "->");
            System.out.println(str_node.length());
            for (int j = 0; j <= str_node.length() - 1; j = j + 2) {
                int t = str_node.charAt(j) - '0';
                System.out.print(t);
            }
            System.out.println();
        }


    }*/

    public void makeGraph(int length)
    {
        int i = 0;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//0-1
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//1-2
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//2-3
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//3-4
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+4));//4-8
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//4-5
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//5-6
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//6-7
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//8-9
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//9-10
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//10-11
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+3));//11-14
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//11-12
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//12-13
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//14-15
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+3));//15-18
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//15-16
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//16-17
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//18-19
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//19-20
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+3));//20-23
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//20-21
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//21-22
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//23-24
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//24-25
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//25-26
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//26-27
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//27-28
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//28-29
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//29-31
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//29-30
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//31-32
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//32-33
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+5));//33-38
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//33-34
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//34-35
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//35-36
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//36-37
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//38-39
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//39-40
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//40-42
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//40-41
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//42-43
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+7));//43-50
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//43-44
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//44-45
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//45-46
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//46-47
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//47-48
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//48-49
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//50-51
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+5));//51-56
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//51-52
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//52-53
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//53-54
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//54-55
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//56-58
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//56-57
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//58-59
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//59-60
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//60-61
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//61-63
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//61-62
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//63-64
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//64-65
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//65-67
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//65-66
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//67-68
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//68-69
        i=i+1;

        addTwoWayVertex(String.valueOf(i),String.valueOf(i+8));//69-77
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+25));//69-94
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//69-70
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//70-71
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//71-72
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//72-73
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//73-74
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//74-75
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//75-76
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//77-78
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//78-79
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//79-80
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//80-81
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//81-82
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//82-83
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//83-84
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//84-85
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//85-86
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//86-87
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//87-88
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//88-89
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//89-90
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//90-91
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//91-92
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//92-93
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//94-95
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+9));//95-104
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//95-96
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//96-97
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//97-98
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//98-99
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//99-100
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//100-101
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//101-102
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//102-103
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//104-105
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//105-106
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//106-107
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//107-108
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//108-109
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//109-110
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//110-111
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//111-112
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//112-113
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//113-114
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//114-115
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//115-117
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//115-116
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//117-118
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//118-119
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//119-120
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//120-121
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//121-122
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//122-123
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//123-124
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//124-125
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//125-126
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+7));//126-133
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//126-127
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//127-128
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//128-129
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//129-130
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//130-131
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//131-132
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//133-134
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//134-135
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//135-136
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//136-137
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//137-138
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//138-139
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+6));//139-145
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//139-140
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//140-141
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//141-142
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//142-143
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//143-144
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+3));//145-148
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//145-146
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//146-147
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//148-149
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+4));//149-153
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//149-150
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//150-151
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//151-152
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//153-154
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(9));//154-9
        i=i+1;
        ////////////////////////////////////////////
        addTwoWayVertex(String.valueOf(24),String.valueOf(i));//24-155
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//155-156
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//156-157
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//157-158
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//158-159
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//159-160
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+3));//160-163
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//160-161
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//161-162
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//163-164
        i=i+1;
        /*
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//164-165
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//165-166
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+3));//166-169
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//166-167
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//167-168
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//169-170
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//170-171
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//171-172
        i=i+1;
        */
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+6));//164-170
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//164-165
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//165-166
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//166-167
        i=i+1;

        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//167-168
        i=i+1;

        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//168-169
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//170-171
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//171-172
        i=i+1;

        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//172-173
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//173-174
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//174-175
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//175-176
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+3));//176-179
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//176-177
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//177-178
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//179-180
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//180-181
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//181-182
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//182-184
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//182-183
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(111));//184-111
        i=i+1;
        /////////////////////////////////////////////////////
        addTwoWayVertex(String.valueOf(137),String.valueOf(i));//185-186
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//186-187
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//187-189
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//187-188
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//189-191
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//189-190
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//191-192
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//192-194
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//192-193
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//194-196
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//194-195
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//196-198
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//196-197
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//198-200
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//198-199
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//200-202
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//200-201
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//202-203
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(174));//203-174
        i=i+1;
        //////////////////////////////////////////////////////////////
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//204-205
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//205-206
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//206-207
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+2));//207-209
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//207-208
        i=i+1;
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//209-210
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(i+1));//210-211
        i=i+1;
        addTwoWayVertex(String.valueOf(i),String.valueOf(61));//211-61
        i=i+1;
        ////////////////////////////////////////////////////////
    }
}