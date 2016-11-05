package cn.xxywithpq.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.store.DataStoreWriter;
import org.springframework.data.hadoop.store.StoreException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.xxywithpq.domain.Icon;

@RestController
@RequestMapping("/hadoop/")
public class HadoopController {

	private DataStoreWriter<Icon> writer;

	@Autowired
	public void setDataStoreWriter(DataStoreWriter<Icon> dataStoreWriter) {
		this.writer = dataStoreWriter;
	}

	@RequestMapping(method = RequestMethod.POST, value = "icon/upload")
	public Object iconUpload(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		JSONObject object = new JSONObject();

		try {
			writer.write(new Icon(file.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			writer.close();
		} catch (IOException e) {
			throw new StoreException("Error closing FileInfo", e);
		}
		object.put("msg", "ok");
		return object;
	}

}
