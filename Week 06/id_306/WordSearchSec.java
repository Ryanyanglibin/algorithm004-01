package sf.week6;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by LynnSun on 2019/11/23.
 * 力扣题目地址：https://leetcode-cn.com/problems/word-search-ii/
 */
public class WordSearchSec {

    /**
     * 字典树方式 DFS
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word: words)
            trie.insert(word);

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        Set<String> resultSet = new HashSet();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j)
                search(board, visited, i, j, m, n, trie.root, resultSet);
        }

        return new LinkedList<String>(resultSet);
    }

    private void search(char[][] board, boolean[][] visited, int i, int j, int m, int n, TrieNoded node, Set<String> result) {
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j])
            return;

        node = node.children[board[i][j] - 'a'];
        if (node == null)
            return;

        if (node.word != null)
            result.add(node.word);

        visited[i][j] = true;
        search(board, visited, i-1, j, m, n, node, result);
        search(board, visited, i+1, j, m, n, node, result);
        search(board, visited, i, j-1, m, n, node, result);
        search(board, visited, i, j+1, m, n, node, result);
        visited[i][j] = false;
    }
}

class Trie {
    public TrieNoded root = new TrieNoded();

    public void insert(String word) {
        TrieNoded node = root;
        int n = word.length();
        int charNo;
        for (int i = 0; i < n; ++i) {
            charNo = word.charAt(i) - 'a';
            if (node.children[charNo] == null)
                node.children[charNo] = new TrieNoded();
            node = node.children[charNo];
        }
        node.word = word;
    }
}

class TrieNoded {
    public TrieNoded[] children = new TrieNoded[26];
    public String word = null;
}