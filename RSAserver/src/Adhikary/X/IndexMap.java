package Adhikary.X;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class IndexMap implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, Long> indexMap = new LinkedHashMap<String, Long> ();
	public Map<String,Long> getIndexMap()
	{
		return indexMap;
	}


	@Override

	public String toString()
	{
		return  "serialVersionUID: "  + serialVersionUID + " indexMap: "  + indexMap;
	}
}
