package com.rhb.shortviedo.controller;


import com.rhb.shortviedo.common.enums.VideoStatusEnum;
import com.rhb.shortviedo.common.utils1.FetchVideoCover;
import com.rhb.shortviedo.common.utils1.JSONResult;
import com.rhb.shortviedo.common.utils1.MergeVideoMp3;
import com.rhb.shortviedo.controller.open.BasicController;
import com.rhb.shortviedo.entity.Bgm;
import com.rhb.shortviedo.entity.Videos;
import com.rhb.shortviedo.service.BgmService;
import com.rhb.shortviedo.service.VideosService;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 视频信息表(Videos)表控制层
 *
 * @author makejava
 * @since 2020-04-06 12:58:50
 */

    @RestController
    @Api(value="视频相关业务的接口", tags= {"视频相关业务的controller"})
    @RequestMapping("/video")
    public class VideosController extends BasicController {

        @Autowired
        private BgmService bgmService;

        @Autowired
        private VideosService videoService;

        @ApiOperation(value="上传视频", notes="上传视频的接口")
        @ApiImplicitParams({
                @ApiImplicitParam(name="userId", value="用户id", required=true,
                        dataType="String", paramType="form"),
                @ApiImplicitParam(name="bgmId", value="背景音乐id", required=false,
                        dataType="String", paramType="form"),
                @ApiImplicitParam(name="videoSeconds", value="背景音乐播放长度", required=true,
                        dataType="String", paramType="form"),
                @ApiImplicitParam(name="videoWidth", value="视频宽度", required=true,
                        dataType="String", paramType="form"),
                @ApiImplicitParam(name="videoHeight", value="视频高度", required=true,
                        dataType="String", paramType="form"),
                @ApiImplicitParam(name="desc", value="视频描述", required=false,
                        dataType="String", paramType="form")
        })
        @PostMapping(value="/upload", headers="content-type=multipart/form-data")
        public JSONResult upload(String userId,
                                 String bgmId, double videoSeconds,
                                 int videoWidth, int videoHeight,
                                 String desc,
                                 @ApiParam(value="短视频", required=true)
                                              MultipartFile file) throws Exception {

            if (StringUtils.isBlank(userId)) {
                return JSONResult.errorMsg("用户id不能为空...");
            }

            // 文件保存的命名空间
//		String fileSpace = "C:/imooc_videos_dev";
            // 保存到数据库中的相对路径
            String uploadPathDB = "/" + userId + "/video";
            String coverPathDB = "/" + userId + "/video";

            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            String finalVideoPath = "";
            // 文件上传的最终保存路径
            try {
                if (file != null) {

                    String fileName = file.getOriginalFilename();
                    // abc.mp4
                    String arrayFilenameItem[] = fileName.split("\\.");
                    String fileNamePrefix = "";
                    for (int i = 0; i < arrayFilenameItem.length - 1; i++) {
                        fileNamePrefix += arrayFilenameItem[i];
                    }
                    // fix bug: 解决小程序端OK，PC端不OK的bug，原因：PC端和小程序端对临时视频的命名不同
			   //String fileNamePrefix = fileName.split("\\.")[0];

                    if (StringUtils.isNotBlank(fileName)) {
                        finalVideoPath = FILE_SPACE + uploadPathDB + "/" + fileName;
                        // 设置数据库保存的路径
                        uploadPathDB += ("/" + fileName);
                         coverPathDB = coverPathDB + "/" + fileNamePrefix + ".jpg";

                        File outFile = new File(finalVideoPath);
                        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                            // 创建父文件夹
                            outFile.getParentFile().mkdirs();
                        }

                        fileOutputStream = new FileOutputStream(outFile);
                        inputStream = file.getInputStream();
                        IOUtils.copy(inputStream, fileOutputStream);
                    }

                } else {
                    return JSONResult.errorMsg("上传出错...");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return JSONResult.errorMsg("上传出错...");
            } finally {
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }

            // 判断bgmId是否为空，如果不为空，
            // 那就查询bgm的信息，并且合并视频，生产新的视频
            System.out.println(bgmId);
            if (StringUtils.isNotBlank(bgmId)) {


                /*Bgm bgm = bgmService.queryBgmById(bgmId);
                String mp3InputPath = FILE_SPACE + bgm.getPath();

                MergeVideoMp3 tool = new MergeVideoMp3(FFMPEG_EXE);
                String videoInputPath = finalVideoPath;

                String videoOutputName = UUID.randomUUID().toString() + ".mp4";
                uploadPathDB = "/" + userId + "/video" + "/" + videoOutputName;
                finalVideoPath = FILE_SPACE + uploadPathDB;
                tool.convertorMerge(videoInputPath, mp3InputPath, videoSeconds, finalVideoPath);
*/
                String tempVideoOutputName = UUID.randomUUID().toString()+"_TEMP" + ".mp4";
                String tempPathDB = FILE_SPACE+"/" + userId + "/video" + "/" + tempVideoOutputName;
                MergeVideoMp3 tool = new MergeVideoMp3(FFMPEG_EXE);
                String videoInputPath = finalVideoPath;
                tool.convertorRemoveMusic(videoInputPath, tempPathDB);
                String videoOutputName = UUID.randomUUID().toString()+"_FINAL" + ".mp4";
                uploadPathDB = "/" + userId + "/video" + "/" + videoOutputName;
                finalVideoPath = FILE_SPACE + uploadPathDB;
                Bgm bgm = bgmService.queryBgmById(bgmId);
                String mp3InputPath ="C:"+bgm.getPath();
               /* System.out.println("秒"+videoSeconds);
                System.out.println("finalVideoPath=" + finalVideoPath);
                System.out.println(tempPathDB);
                System.out.println(mp3InputPath);*/
                tool.convertorMerge(tempPathDB,mp3InputPath,videoSeconds,finalVideoPath);
            }
            // 对视频进行截图
            FetchVideoCover videoInfo = new FetchVideoCover(FFMPEG_EXE);
            videoInfo.getCover(finalVideoPath, FILE_SPACE + coverPathDB);


            Videos video = new Videos();
            video.setAudioId(bgmId);
            video.setUserId(userId);
            video.setVideoSeconds((float)videoSeconds);
            video.setVideoHeight(videoHeight);
            video.setVideoWidth(videoWidth);
            video.setVideoDesc(desc);
            video.setVideoPath(uploadPathDB);
            video.setCoverPath(coverPathDB);
            video.setStatus(VideoStatusEnum.SUCCESS.value);
            video.setCreateTime(new Date());

            String videoId = videoService.saveVideo(video);
            return JSONResult.ok(videoId);
        }

    @ApiOperation(value="上传封面", notes="上传封面的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId", value="用户id", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoId", value="视频主键id", required=true,
                    dataType="String", paramType="form")
    })
    @PostMapping(value="/uploadCover", headers="content-type=multipart/form-data")
    public JSONResult uploadCover(String userId,
                                       String videoId,
                                       @ApiParam(value="视频封面", required=true)
                                               MultipartFile file) throws Exception {

        if (StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("视频主键id和用户id不能为空...");
        }
        // 文件保存的命名空间
//		String fileSpace = "C:/imooc_videos_dev";
        // 保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        // 文件上传的最终保存路径
        String finalCoverPath = "";
        try {
            if (file != null) {

                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {

                    finalCoverPath = FILE_SPACE + uploadPathDB + "/" + fileName;
                    // 设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalCoverPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        // 创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }

            } else {
                return JSONResult.errorMsg("上传出错...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传出错...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        videoService.updateVideo(videoId, uploadPathDB);

        return JSONResult.ok();
    }











}
