/*
 Navicat Premium Data Transfer
 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : fu-cloud
 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bms_billboard
-- ----------------------------
DROP TABLE IF EXISTS `bms_billboard`;
CREATE TABLE `bms_billboard`  (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `content` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '公告',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '公告时间',
                                  `show` tinyint(1) NULL DEFAULT NULL COMMENT '1：展示中，0：过期',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4  COMMENT = '全站公告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_billboard
-- ----------------------------
INSERT INTO `bms_billboard` VALUES (2, '愿乘冷风去，直出福云间', '2022-11-9 23:16:19', 1);
INSERT INTO `bms_billboard` VALUES (4, '系统已更新至最新版1.0.1', NULL, 0);


-- ----------------------------
-- Table structure for bms_follow
-- ----------------------------
DROP TABLE IF EXISTS `bms_follow`;
CREATE TABLE `bms_follow`  (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `parent_id` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '被关注人ID',
                               `follower_id` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '关注人ID',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8mb4  COMMENT = '用户关注' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_follow
-- ----------------------------
-- INSERT INTO `bms_follow` VALUES (65, '1329723594994229250', '1317498859501797378');
-- INSERT INTO `bms_follow` VALUES (85, '1332912847614083073', '1332636310897664002');
-- INSERT INTO `bms_follow` VALUES (129, '1349290158897311745', '1349618748226658305');

-- ----------------------------
-- Table structure for bms_post
-- ----------------------------
DROP TABLE IF EXISTS `bms_post`;
CREATE TABLE `bms_post`  (
                             `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                             `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
                             `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'markdown内容',
                             `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者ID',
                             `comments` int NOT NULL DEFAULT 0 COMMENT '评论统计',
                             `collects` int NOT NULL DEFAULT 0 COMMENT '收藏统计',
                             `view` int NOT NULL DEFAULT 0 COMMENT '浏览统计',
                             `top` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否置顶，1-是，0-否',
                             `essence` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否加精，1-是，0-否',
                             `section_id` int NULL DEFAULT 0 COMMENT '问答id',
                             `create_time` datetime NOT NULL COMMENT '发布时间',
                             `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
                             `cover` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/img/cover/fu-cloud-org.png' COMMENT '封面',
                             `summary` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '不写简介的帖子可不是一篇好帖子欧' COMMENT '简介',
                             INDEX `title`(`title`) USING BTREE,
                             INDEX `user_id`(`user_id`) USING BTREE,
                             INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '帖子表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_post
-- ----------------------------
INSERT INTO `bms_post` VALUES ('1333447953558765569', '1', '12\n2\n\n', '1349290158897311745', 0, 0, 77, b'0', b'0', 0, '2022-11-01 00:29:01', '2022-11-03 23:56:51', '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1349362401438392322', '2021 健康，快乐', '2021的`FLAG`\n\n1. 技能进步\n2. 没有烦恼\n3. 发财 :smile:\n\n', '1349290158897311745', 0, 0, 21, b'0', b'0', 0, '2021-01-13 22:27:21', '2021-01-14 17:30:13', '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1334481725322297346', 'hello，spring-security', ':hibiscus: spring-security\n\n', '1349290158897311745', 0, 0, 46, b'0', b'0', 0, '2022-11-03 20:56:51', NULL,'/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1332650453142827009', '哈哈哈，helloworld', '这是第一篇哦\n\n> hi :handshake: 你好\n\n`hello world`\n\n:+1: 很好\n', '1349290158897311745', 0, 0, 29, b'0', b'0', 1, '2022-10-28 19:40:02', '2022-10-28 19:46:39', '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1333432347031646209', '哈哈哈，换了个dark主题', '主题更换为Dark\n\n', '1349290158897311745', 0, 0, 6, b'0', b'0', 0, '2022-10-30 23:27:00', NULL, '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1333668258587750401', '嘿嘿，测试一下啊', '大家好\n`Hello everyone!`\n\n\n\n', '1349290158897311745', 0, 0, 7, b'0', b'0', 0, '2022-11-01 15:04:26', '2022-11-01 16:49:14', '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1332682473151635458', '我要发财', '2021 冲冲冲！！！\n\n', '1349290158897311745', 0, 0, 94, b'0', b'0', 2, '2022-10-28 21:47:16', '2022-10-30 19:40:22', '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
-- INSERT INTO `bms_post` VALUES ('1333695976536748034', '最新版本介绍，同步更新！', '<p align=center>一款基于SpringBoot构建的智慧社区系统</p>\n\n<p align=center>\n<a href=\"https://github.com/1020317774/rhapsody-admin/stargazers\"><img alt=\"GitHub release\" src=\"https://img.shields.io/github/release/1020317774/rhapsody-admin?style=flat-square\"></a>\n<a href=\"https://github.com/1020317774/rhapsody-admin/blob/main/LICENSE\"><img alt=\"GitHub license\" src=\"https://img.shields.io/github/license/1020317774/rhapsody-admin\"></a>\n</p>\n\n### Hi there :wave:\n\n<!--\n**1020317774/1020317774** is a :sparkles: _special_ :sparkles: repository because its `README.md` (this file) appears on your GitHub profile.\n\nHere are some ideas to get you started:\n\n- :telescope: I’m currently working on ...\n- :seedling: I’m currently learning ...\n- :dancers: I’m looking to collaborate on ...\n- :thinking: I’m looking for help with ...\n- :speech_balloon: Ask me about ...\n- :mailbox: How to reach me: ...\n- :smile: Pronouns: ...\n- :zap: Fun fact: ...\n-->\n\n[![1020317774\'s github stats](https://github-readme-stats.vercel.app/api?username=1020317774&show_icons=true&count_private=true)](https://github.com/1020317774)\n\n[![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=1020317774&layout=compact)](https://github.com/1020317774)\n---------\n\n> 作者：王一晨\n> github：[https://github.com/1020317774](https://github.com/1020317774)\n\n## 技术栈\n\n- [x] SpringBoot 2.X\n- [x] Mysql 8.X\n- [x] Mybatis\n- [x] MybatisPlus\n- [x] Redis\n- [x] Jwt\n- [x] FastJson\n- [x] Hutool\n- [x] Lombok\n- [ ] ElasticSearch\n\n……\n\n## 安装指导\n\n- 克隆\n\n```java\ngit clone https://github.com/1020317774/rhapsody-admin.git\n```\n\n- 修改`application.yml`选择环境\n- 修改多环境配置中的redis参数和数据库\n- 启动`BootApplication`\n- 访问[`http://127.0.0.1:10000`](http://127.0.0.1:10000)\n\n', '1349290158897311745', 0, 0, 45, b'1', b'1', 0, '2022-12-01 16:54:34', '2022-12-01 17:05:00', '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1349631541260595202', '权限部分 OK', '1. 创建 ok\n2. 修改 ok\n3. 删除 ok\n\n', '1349290158897311745', 0, 0, 17, b'0', b'0', 0, '2021-01-14 16:16:49', '2021-01-14 16:18:53', '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1333676096156528641', '测试', '测试\n\n', '1349290158897311745', 0, 0, 38, b'0', b'0', 0, '2022-11-01 15:35:34', NULL, '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1332681213400817665', '聚合查询并统计', '* [x] SQL：\n\n```sql\nSELECT s.*,\nCOUNT(t.id) AS topics\nFROM section s\nLEFT JOIN topic t\nON s.id = t.section_id\nGROUP BY s.title\n```\n\n', '1349290158897311745', 0, 0, 55, b'0', b'0', 1, '2022-10-28 21:42:16', '2022-10-29 15:00:42', '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');
INSERT INTO `bms_post` VALUES ('1335149981733449729', '视频嵌入', ':+1:\n\n[https://www.bilibili.com/video/BV1w64y1f7w3](https://www.bilibili.com/video/BV1w64y1f7w3)\n\n[1](https://www.bilibili.com/video/BV1tp4y1x72w)\n\n```\n.vditor-reset pre > code\n```\n\n```\npublic class HelloWorld {\n\npublic static void main(String[] args) {\n    System.out.println(\"Hello World!\");\n}\n}\n```\n\n', '1349290158897311745', 0, 0, 41, b'0', b'0', 0, '2022-11-05 17:12:16', '2022-01-14 13:06:16', '/img/cover/fu-cloud-org.png', '不写简介的帖子可不是一篇好帖子欧');


-- ----------------------------
-- Table structure for bms_comment
-- ----------------------------
DROP TABLE IF EXISTS `bms_comment`;
CREATE TABLE `bms_comment`  (
                                `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内容',
                                `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者ID',
                                `post_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子id',
                                `create_time` datetime NOT NULL COMMENT '发布时间',
                                `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for bms_tag
-- ----------------------------
DROP TABLE IF EXISTS `bms_tag`;
CREATE TABLE `bms_tag`  (
                            `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
                            `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签',
                            `post_count` int NOT NULL DEFAULT 0 COMMENT '关联帖子数',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_tag
-- ----------------------------
INSERT INTO `bms_tag` VALUES ('1332650453377708034', 'java', 1);
INSERT INTO `bms_tag` VALUES ('1332681213568589825', 'css', 1);
INSERT INTO `bms_tag` VALUES ('1332681213631504385', 'mongodb', 1);
INSERT INTO `bms_tag` VALUES ('1332682473218744321', 'python', 1);
INSERT INTO `bms_tag` VALUES ('1332913064463794178', 'vue', 2);
INSERT INTO `bms_tag` VALUES ('1332913064530903041', 'react', 1);
INSERT INTO `bms_tag` VALUES ('1333432347107143681', 'node', 1);
INSERT INTO `bms_tag` VALUES ('1333432347107143682', 'mysql', 1);
INSERT INTO `bms_tag` VALUES ('1333447953697177602', 'flask', 2);
INSERT INTO `bms_tag` VALUES ('1333676096320106498', 'spring', 1);
INSERT INTO `bms_tag` VALUES ('1333695976742268930', 'django', 1);
INSERT INTO `bms_tag` VALUES ('1334481725519429634', 'security', 1);
INSERT INTO `bms_tag` VALUES ('1349362401597775874', 'tensorflow', 1);
INSERT INTO `bms_tag` VALUES ('1349631541306732545', 'pytorch', 1);


-- ----------------------------
-- Table structure for bms_post_tag
-- ----------------------------
DROP TABLE IF EXISTS `bms_post_tag`;
CREATE TABLE `bms_post_tag`  (
                                 `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `tag_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
                                 `post_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子id',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `tag_id`(`tag_id`) USING BTREE,
                                 INDEX `post_id`(`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '帖子-标签 中间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_post_tag
-- ----------------------------
INSERT INTO `bms_post_tag` VALUES (36, '1332650453377708034', '1332650453142827009');
INSERT INTO `bms_post_tag` VALUES (37, '1332681213568589825', '1332681213400817665');
INSERT INTO `bms_post_tag` VALUES (38, '1332681213631504385', '1332681213400817665');
INSERT INTO `bms_post_tag` VALUES (39, '1332682473218744321', '1332682473151635458');
INSERT INTO `bms_post_tag` VALUES (40, '1332913064463794178', '1332913064396685314');
INSERT INTO `bms_post_tag` VALUES (41, '1332913064530903041', '1332913064396685314');
INSERT INTO `bms_post_tag` VALUES (42, '1333432347107143681', '1333432347031646209');
INSERT INTO `bms_post_tag` VALUES (43, '1333432347107143682', '1333432347031646209');
INSERT INTO `bms_post_tag` VALUES (44, '1333447953697177602', '1333447953558765569');
INSERT INTO `bms_post_tag` VALUES (45, '1332913064463794178', '1333668258587750401');
INSERT INTO `bms_post_tag` VALUES (46, '1333676096320106498', '1333676096156528641');
INSERT INTO `bms_post_tag` VALUES (47, '1333695976742268930', '1333695976536748034');
INSERT INTO `bms_post_tag` VALUES (48, '1334481725519429634', '1334481725322297346');
INSERT INTO `bms_post_tag` VALUES (49, '1333447953697177602', '1335149981733449729');
INSERT INTO `bms_post_tag` VALUES (50, '1349362401597775874', '1349362401438392322');
INSERT INTO `bms_post_tag` VALUES (51, '1349631541306732545', '1349631541260595202');

-- ----------------------------
-- Table structure for bms_promotion
-- ----------------------------
DROP TABLE IF EXISTS `bms_promotion`;
CREATE TABLE `bms_promotion`  (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `title` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '广告标题',
                                  `link` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '广告链接',
                                  `description` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '说明',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4  COMMENT = '广告推广表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_promotion
-- ----------------------------
INSERT INTO `bms_promotion` VALUES (1, 'Attensist', 'https://blog.csdn.net/m0_52119324', 'Attensist');
INSERT INTO `bms_promotion` VALUES (2, 'yang三毛', 'https://blog.csdn.net/y15659037739l', 'yang三毛');
INSERT INTO `bms_promotion` VALUES (3, 'xilili24', 'https://blog.csdn.net/m0_62111261', 'xilili24');
INSERT INTO `bms_promotion` VALUES (4, 'Yeyeahl', 'https://blog.csdn.net/m0_62064405', 'Yeyeahl');
INSERT INTO `bms_promotion` VALUES (5, 'Jin4869', 'https://blog.csdn.net/jin4869', 'Jin4869');
INSERT INTO `bms_promotion` VALUES (6, '今天什么歌', 'https://blog.csdn.net/weixin_55703999', '今天什么歌');
INSERT INTO `bms_promotion` VALUES (7, 'xzy', 'https://blog.csdn.net/t_eddy', 'xzy');
INSERT INTO `bms_promotion` VALUES (8, 'dofson', 'https://blog.csdn.net/hhwzs', 'dofson');
INSERT INTO `bms_promotion` VALUES (9, '安康', 'https://blog.csdn.net/weixin_51744713', '安康');
INSERT INTO `bms_promotion` VALUES (10, '硫没有正七价', 'https://weapons.schwefel.me/', '硫没有正七价');


-- ----------------------------
-- Table structure for bms_tip
-- ----------------------------
DROP TABLE IF EXISTS `bms_tip`;
CREATE TABLE `bms_tip`  (
                            `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内容',
                            `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '作者',
                            `type` tinyint NOT NULL COMMENT '1：使用，0：过期',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24864 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '每日赠言' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bms_tip
-- ----------------------------
INSERT INTO `bms_tip` VALUES (1, '多锉出快锯，多做长知识。', '佚名', 1);
INSERT INTO `bms_tip` VALUES (2, '未来总留着什么给对它抱有信心的人。', '佚名', 1);
INSERT INTO `bms_tip` VALUES (3, '一个人的智慧不够用，两个人的智慧用不完。', '谚语', 1);
INSERT INTO `bms_tip` VALUES (4, '十个指头按不住十个跳蚤', '傣族', 1);
INSERT INTO `bms_tip` VALUES (5, '言不信者，行不果。', '墨子', 1);
INSERT INTO `bms_tip` VALUES (6, '攀援而登，箕踞而遨，则几数州之土壤，皆在衽席之下。', '柳宗元', 1);
INSERT INTO `bms_tip` VALUES (7, '美德大都包含在良好的习惯之内。', '帕利克', 1);
INSERT INTO `bms_tip` VALUES (8, '人有不及，可以情恕。', '《晋书》', 1);
INSERT INTO `bms_tip` VALUES (9, '明·吴惟顺', '法不传六耳', 1);
INSERT INTO `bms_tip` VALUES (10, '真正的朋友应该说真话，不管那话多么尖锐。', '奥斯特洛夫斯基', 1);
INSERT INTO `bms_tip` VALUES (11, '时间是一切财富中最宝贵的财富。', '德奥弗拉斯多', 1);
INSERT INTO `bms_tip` VALUES (12, '看人下菜碟', '民谚', 1);
INSERT INTO `bms_tip` VALUES (13, '如果不是怕别人反感，女人决不会保持完整的严肃。', '拉罗什福科', 1);
INSERT INTO `bms_tip` VALUES (14, '爱是春暖花开时对你满满的笑意', '佚名', 1);
INSERT INTO `bms_tip` VALUES (15, '希望是坚韧的拐杖，忍耐是旅行袋，携带它们，人可以登上永恒之旅。', '罗素', 1);
INSERT INTO `bms_tip` VALUES (18, '天国般的幸福，存在于对真爱的希望。', '佚名', 1);
INSERT INTO `bms_tip` VALUES (19, '我们现在必须完全保持党的纪律，否则一切都会陷入污泥中。', '马克思', 1);
INSERT INTO `bms_tip` VALUES (20, '在科学上没有平坦的大道，只有不畏劳苦沿着陡峭山路攀登的人，才有希望达到光辉的顶点。', '马克思', 1);
INSERT INTO `bms_tip` VALUES (21, '懒惰的马嫌路远', '蒙古', 1);
INSERT INTO `bms_tip` VALUES (22, '别忘记热水是由冷水烧成的', '非洲', 1);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
                            `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                            `post_id` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT ' 帖子id',
                            `user_id` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '点赞用户id',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8mb4  COMMENT = '收藏' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
                             `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
                             `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
                             `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                             `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
                             `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/img/avatar/fu-cloud-icon.png' COMMENT '头像',
                             `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                             `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
                             `score` int NOT NULL DEFAULT 0 COMMENT '积分',
                             `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'token',
                             `dep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
                             `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否激活，1：是，0：否',
                             `status` bit(1) NULL DEFAULT b'1' COMMENT '状态，1：使用，0：停用',
                             `role_id` int NULL DEFAULT NULL COMMENT '用户角色',
                             `create_time` datetime NOT NULL COMMENT '加入时间',
                             `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
                             `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '这个用户很懒！没有留下签名' COMMENT '签名',
                             `sex` smallint NULL DEFAULT 2 COMMENT '性别，0：女，1：男，2：保密',
                             `regional` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `user_name`(`username`) USING BTREE,
                             CHECK(`sex` in (0, 1, 2)),
                             INDEX `user_email`(`email`) USING BTREE,
                             INDEX `user_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES ('1349290158897311745', 'admin', 'admin', '$2a$10$8qx711TBg/2hxfL7N.sxf.0ROMhR/iuPhQx33IFqGd7PLgt5nGJTO', '/img/avatar/fu-cloud-icon.png', '23456@qq.com', NULL, 2, '', '自由职业者', b'1', b'1', NULL, '2021-01-13 17:40:17', NULL, '',2, NULL);
INSERT INTO `ums_user` VALUES ('1595254537973821441', 'schwefel', 'schwefel', '267c24c85343017f14c74d51ed4d5dcf', '/img/avatar/fu-cloud-icon.png', '032004123@fzu.edu.cn', NULL, 0, '', '数据科学与大数据技术', b'1', b'1', NULL, '2021-01-14 15:25:59', NULL, '这个用户很懒！没有留下签名', 1, '福建省-漳州市');

SET FOREIGN_KEY_CHECKS = 1;