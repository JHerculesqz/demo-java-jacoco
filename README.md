#测试分支覆盖率统计工具#
##设计目标##

	对于一个运行的服务，实现一个具有监控功能的server，统计一段时间内调用者访问的代码分支，并输出统计结果。

##实现原理##
####1.提供监控功能的CoverageMonitor####
（1）开源工具jacoco

	jacoco是对EclEmma的进一步封装，使其更易调用，能监控一个运行的服务中调用者访问Restful接口时访问的代码分支；EclEmma是对Emma的进一步封装，EclEmma主要提供在Eclipse中运行时的代码分支覆盖的统计。

（2）jacoco使用方法

jacoco提供了多种使用方式，包括AntTask、MavenPlugin的使用方法，此Demo中使用的是JAVA Agent的方式，即和要监控的服务放在同一个JVM中，在JVM启动时引用
	
	-javaagent:[yourpath/]jacocoagent.jar
即可监控JVM中具体哪些代码被执行。

（3）输出结果jacoco.exec

jacoco支持输出到本地，也支持输出到一个远程服务器的指定目录下，可以通过上面命令调用时设定具体的输出方式，默认输出jacoco.exec。

####2.输出统计结果####
CoverageMonitor输出的是jacoco.exec文件，是一个二进制文件，无法直观的显示统计结果，jacoco提供了一些API用来转换jacoco.exec文件，支持csv.html.xml等文件。本Demo中直接使用了jacoco提供的example，提供了jacoco-reporter，实现根据jacoco.exec生成html。

	jacoco-reporter工程中，均使用的是jacoco提供的代码，仅修改了ReportGenerator.java中的相关路径的设置,最后输出的生成文件在test_report目录中
	public ReportGenerator(final File projectDirectory) {
		this.title = projectDirectory.getName();
		this.executionDataFile = new File(projectDirectory, "jacoco.exec");
		this.classesDirectory = new File(projectDirectory, "build/classes");
		this.sourceDirectory = new File(projectDirectory, "src/main/java");
		this.reportDirectory = new File(projectDirectory, "test_report");
	}

####3.使用方法####
（1）在demo-java-jacoco目录中，使用相关bat进行相关编译构建，并将生成的jar运行起来，其中3.docker.bat是正常jar包的启动脚本，3.docker_test.bat是引入了jacoco监控，此种方式启动时，可以看到当前目录下生成了jacoco.exec文件。

（2）在http://localhost:7000/ui/index.html页面进行访问，分别进行分支覆盖
	
	public class Subprovider {
		// #region hello
	
		public static String hello(String strText) {
			if (strText.contains("1")) {
				System.out.println("true...");
				return "true";
			} else {
				System.out.println("false...");
				return "false";
			}
		}
	
		// #endregion
	}

（3）停止此服务，因为停止服务后，数据统计结果才记录到jacoco.exec文件中。进入demo-jacoco-lib，运行jacoco-reporter.bat,将demo-java-jacoco目录下的jacoco.exec文件生成相关html到test_report文件夹下，访问index.html即可看到具体的代码覆盖结果。

####4.注意####
（1）停止服务后，数据统计结果才记录到jacoco.exec文件中；

（2）重新启动demo-java-jacoco的服务，继续访问，jacoco.exec可以持续记录访问结果。


##reference##

http://www.eclemma.org/jacoco/

http://rensanning.iteye.com/blog/2002371