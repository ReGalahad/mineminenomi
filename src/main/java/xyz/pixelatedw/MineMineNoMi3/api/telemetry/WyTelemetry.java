package xyz.pixelatedw.MineMineNoMi3.api.telemetry;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;

public class WyTelemetry
{

	private static String urlConnection;
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	private static Gson gson = new Gson();
	static
	{
		if (WyDebug.isDebug())
			urlConnection = "http://wynd.go.ro/mmnm-webserver/api";
		else
			urlConnection = "http://pixelatedw.xyz/api";
	}

	public static void sendStructureStat(String id, String name, int value)
	{
		StatData data = new StatData(id, name, value, 0);
		sendData("/addStructureStat", gson.toJson(data));
	}
	
	public static void sendKillStat(String id, String name, int value)
	{
		StatData data = new StatData(id, name, value, 0);
		sendData("/addKillStat", gson.toJson(data));
	}
	
	public static void sendAbilityStat(String id, String name, int value)
	{
		StatData data = new StatData(id, name, value, 0);
		sendData("/addAbilityStat", gson.toJson(data));
	}
	
	public static void sendMiscStat(String id, String name, int value)
	{
		StatData data = new StatData(id, name, value, 0);
		sendData("/addMiscStat", gson.toJson(data));
	}
	
	public static void sendDevilFruitStat(String id, String name, int value)
	{
		StatData data = new StatData(id, name, value, 0);
		sendData("/addDFStat", gson.toJson(data));
	}
	
	private static void sendData(String url, String json)
	{
		try
		{
			HttpPost post = new HttpPost(urlConnection + "" + url);
			StringEntity postingString;
			postingString = new StringEntity(json);
			WyDebug.debug(json);
			post.setEntity(postingString);
			post.setHeader("Content-type", "application/json");
			HttpResponse response = httpClient.execute(post);
			WyDebug.debug(response);
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
			WyDebug.debug(body);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static class StatData
	{
		private String id;
		private String name;
		private int value;
		private int source;
		
		public StatData(String id, String name, int value, int source)
		{
			this.id = id;
			this.name = name;
			this.value = value;
			this.source = source;
		}
	}
}
