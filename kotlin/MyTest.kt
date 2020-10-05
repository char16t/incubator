package training

import io.kotlintest.specs.StringSpec
import java.util.*
import kotlin.collections.HashMap

class MyTest : StringSpec({
    "Test1" {
        val graph = listOf(
            // 1-st connected component
            listOf(1, 2),
            listOf(1, 3),
            listOf(2, 4),
            listOf(2, 5),
            listOf(3, 6),
            listOf(3, 7),

            // 2-nd connected component
            listOf(8, 9)
        )

        fun bfs(root: Int, graph: List<List<Int>>): List<Int> {
            val answer = LinkedList<Int>()
            val queue = LinkedList<Int>()
            queue.add(root)

            val visited = HashMap<Int, Boolean>()
            visited[root] = true

            while (!queue.isEmpty()) {
                val element = queue.removeAt(0)
                answer.add(element)

                val neighbors = graph
                    .filter { pair -> pair[0] != pair[1] && (pair[0] == element || pair[1] == element) }
                    .map { pair -> if (pair[0] != element) pair[0] else pair[1] }
                    .distinct()
                for (neighbor in neighbors) {
                    if (visited.containsKey(neighbor) && !visited[neighbor]!! || !visited.containsKey(neighbor)) {
                        visited[neighbor] = true
                        queue.add(neighbor)
                    }
                }
            }
            return answer
        }

        fun dfs(root: Int, graph: List<List<Int>>): List<Int> {
            val answer = LinkedList<Int>()
            val queue = LinkedList<Int>()
            queue.add(0, root)

            val visited = HashMap<Int, Boolean>()
            visited[root] = true

            while (!queue.isEmpty()) {
                val element = queue.removeAt(0)
                answer.add(element)

                val neighbors = graph
                    .filter { pair -> pair[0] != pair[1] && (pair[0] == element || pair[1] == element) }
                    .map { pair -> if (pair[0] != element) pair[0] else pair[1] }
                    .distinct()
                for (neighbor in neighbors) {
                    if (visited.containsKey(neighbor) && !visited[neighbor]!! || !visited.containsKey(neighbor)) {
                        visited[neighbor] = true
                        queue.add(0, neighbor)
                    }
                }
            }
            return answer
        }

        fun topological_sort(root: Int, graph: List<List<Int>>): List<Int> {
            val answer = LinkedList<Int>()
            val queue = LinkedList<Int>()
            queue.add(0, root)

            val visited = HashMap<Int, Boolean>()
            visited[root] = true

            while (!queue.isEmpty()) {
                val element = queue.removeAt(0)
                answer.add(0, element)

                val neighbors = graph
                    .filter { pair -> pair[0] != pair[1] && (pair[0] == element || pair[1] == element) }
                    .map { pair -> if (pair[0] != element) pair[0] else pair[1] }
                    .distinct()
                for (neighbor in neighbors) {
                    if (visited.containsKey(neighbor) && !visited[neighbor]!! || !visited.containsKey(neighbor)) {
                        visited[neighbor] = true
                        queue.add(0, neighbor)
                    }
                }
            }
            return answer
        }

        fun connected_components(graph: List<List<Int>>): List<List<Int>> {
            val visited = HashMap<Int, Boolean>()
            fun innerDfs(root: Int, graph: List<List<Int>>): List<Int> {
                val answer = LinkedList<Int>()
                val queue = LinkedList<Int>()
                queue.add(0, root)

                visited[root] = true

                while (!queue.isEmpty()) {
                    val element = queue.removeAt(0)
                    answer.add(element)

                    val neighbors = graph
                        .filter { pair -> pair[0] != pair[1] && (pair[0] == element || pair[1] == element) }
                        .map { pair -> if (pair[0] != element) pair[0] else pair[1] }
                        .distinct()
                    for (neighbor in neighbors) {
                        if (visited.containsKey(neighbor) && !visited[neighbor]!! || !visited.containsKey(neighbor)) {
                            visited[neighbor] = true
                            queue.add(0, neighbor)
                        }
                    }
                }
                return answer
            }

            val components = LinkedList<List<Int>>()
            val vertices = graph.flatten().distinct()
            for (vertice in vertices) {
                if (visited.containsKey(vertice) && !visited[vertice]!! || !visited.containsKey(vertice)) {
                    components.add(innerDfs(vertice, graph))
                }
            }
            return components
        }

        // https://neerc.ifmo.ru/wiki/index.php?title=%D0%98%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5_%D0%BE%D0%B1%D1%85%D0%BE%D0%B4%D0%B0_%D0%B2_%D0%B3%D0%BB%D1%83%D0%B1%D0%B8%D0%BD%D1%83_%D0%B4%D0%BB%D1%8F_%D0%BF%D0%BE%D0%B8%D1%81%D0%BA%D0%B0_%D0%BA%D0%BE%D0%BC%D0%BF%D0%BE%D0%BD%D0%B5%D0%BD%D1%82_%D1%81%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE%D0%B9_%D1%81%D0%B2%D1%8F%D0%B7%D0%BD%D0%BE%D1%81%D1%82%D0%B8
        fun strong_connected_components(root: Int, graph: List<List<Int>>): List<List<Int>> {
            val visited = HashMap<Int, Boolean>()
            fun innerDfs(root: Int, graph: List<List<Int>>): List<Int> {
                val answer = LinkedList<Int>()
                val queue = LinkedList<Int>()
                queue.add(0, root)

                visited[root] = true

                while (!queue.isEmpty()) {
                    val element = queue.removeAt(0)
                    answer.add(element)

                    val neighbors = graph
                        .filter { pair -> pair[0] != pair[1] && pair[0] == element }
                        .map { it[1] }
                        .distinct()
                    for (neighbor in neighbors) {
                        if (visited.containsKey(neighbor) && !visited[neighbor]!! || !visited.containsKey(neighbor)) {
                            visited[neighbor] = true
                            queue.add(0, neighbor)
                        }
                    }
                }
                return answer
            }

            val reversedGraph = graph.map { it.reversed() }
            val vertices = connected_components(graph).flatten() //.map { it.reversed() }
            //val vertices = dfs(root, graph)

            val components = LinkedList<List<Int>>()
            for (vertice in vertices) {
                if (visited.containsKey(vertice) && !visited[vertice]!! || !visited.containsKey(vertice)) {
                    components.add(innerDfs(vertice, reversedGraph))
                }
            }
            return components
        }
        
        println(bfs(1, graph))
        println("---")
        println(dfs(1, graph))
        println("---")
        println(topological_sort(1, graph))
        println("---")
        println(connected_components(graph))
        println("---")
        println(strong_connected_components(1, graph))
    }
})
