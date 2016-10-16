import java.util.HashMap;

public class SuffixTrie {

	public class SuffixNode {
		public HashMap<Character, SuffixNode> children;

		public String suffix;

		public SuffixNode suffix_link;

		public SuffixNode parent;

		public int CountOfLeaves = -1;

		public SuffixNode() {
			this.suffix_link = this;
			this.children = new HashMap<Character, SuffixNode>();
		}

		public SuffixNode(SuffixNode suffix_link) {

			this.suffix_link = suffix_link;
			this.children = new HashMap<Character, SuffixNode>();

		}

		public void AddLink(Character c, SuffixNode target) {
			children.put(c, target);
		}

	}

	public SuffixNode root;

	public SuffixNode longest;

	public SuffixTrie() {

		this.root = new SuffixNode();

		this.longest = new SuffixNode(this.root);

	}

	public void Build(String s) {
		this.root = new SuffixNode();
		for (int i = 0; i < s.length(); i++) {
			SuffixNode current = this.root;
			for (int j = i; j < s.length(); j++) {
				Character ch = s.charAt(j);
				if (!current.children.containsKey(ch)) {
					SuffixNode add = new SuffixNode();
					current.children.put(ch, add);
				}
				current = current.children.get(ch);
			}
		}
		
	}

	public boolean Contains(String q) {
		SuffixNode current = this.root;
		for (int c = 0; c < q.length(); c++) {
			Character ch = q.charAt(c);
			if (current.children.containsKey(ch)) {
				current = current.children.get(ch);
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean Suffix(String q) {
		SuffixNode current = this.root;
		for (int c = 0; c < q.length(); c++) {
			Character ch = q.charAt(c);
			if (current.children.containsKey(ch)) {
				current = current.children.get(ch);
			} else {
				return false;
			}
		}

		if (current.children.size() == 0 || !current.children.containsKey('$')) {
			return false;
		}

		return true;
	}

	public int CountOfLeaves(SuffixNode current) {
		if (current.children.size() == 0) {
			current.CountOfLeaves = 0;
			return 1;
		}

		if (current.CountOfLeaves != -1) {
			return current.CountOfLeaves;
		}

		int sum = 0;
		for (SuffixNode c : current.children.values()) {
			sum += CountOfLeaves(c);
		}

		current.CountOfLeaves = sum;

		return sum;
	}

	public int CountOf(String q) {
		SuffixNode current = this.root;
		for (int c = 0; c < q.length(); c++) {
			Character ch = q.charAt(c);
			if (current.children.containsKey(ch)) {
				current = current.children.get(ch);
			} else {
				return 0;
			}
		}

		return CountOfLeaves(current);
	}

	public String LongestRepeat() {
		SuffixNode current = this.longest;
		System.out.println(current.suffix);
		while (current != null && CountOfLeaves(current) < 2) {
			current = current.parent;
		}

		if (current != null) {
			return current.suffix;
		} else {
			return "";
		}
	}
	
	public void print(SuffixNode n) {
		System.out.println(n + " " + n.children.size() + " " + n.suffix);
		System.out.println(n.children + "\n");
		for (SuffixNode m : n.children.values()) {
			print(m);
		}
	}

	public static void main(String[] args) {
		SuffixTrie n = new SuffixTrie();
		n.Build("abaaba$");
		System.out.println(n.Contains("abaa"));
		System.out.println(n.Contains("aab"));
		System.out.println(n.Contains("aba"));
		System.out.println(n.Contains("ab"));
		System.out.println(n.Contains("ba"));
		System.out.println(n.Suffix("abaa"));
		System.out.println(n.Suffix("aab"));
		System.out.println(n.Suffix("aba"));
		System.out.println(n.Suffix("ab"));
		System.out.println(n.Suffix("ba"));
		System.out.println(n.CountOf("a"));
		System.out.println(n.CountOf("b"));
	}
}
