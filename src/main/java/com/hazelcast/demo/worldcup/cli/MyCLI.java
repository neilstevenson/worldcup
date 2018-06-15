package com.hazelcast.demo.worldcup.cli;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.demo.worldcup.jet.TwtiterPipeline;
import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.Job;
import com.hazelcast.jet.config.JobConfig;
import com.hazelcast.jet.pipeline.Pipeline;

/**
 * <p>
 * Extend the basic commands provided by <a href="https://projects.spring.io/spring-shell/">Spring Shell</a>,
 * such as "{@code help}" and "{@code quit}" with some ones to control
 * this application.
 * </p>
 */

@ShellComponent
public class MyCLI {
	
	@Autowired
	public HazelcastInstance hazelcastInstance;
	@Autowired
	public JetInstance jetInstance;
	@Autowired
	public TwtiterPipeline twitterPipeline;
	
	private Job twitterPipelineJob = null;

	/**
	 * <p>
	 * List the jobs in Jet, started, running and completed.
	 * </p>
	 */
	@ShellMethod(key = "JOBS", value = "List all jobs")
	public void jobs() {
		AtomicLong count = new AtomicLong();

		this.jetInstance.getJobs()
		.stream()
		.collect(Collectors.toCollection(() -> new TreeSet<Job>(Comparator.comparing(Job::getSubmissionTime))))
		.stream()
        .forEach(job -> {
        	count.incrementAndGet();
            System.out.printf("Job name '%s' id '%d' status '%s', submitted %s%n",
                                job.getName(), job.getId(), job.getStatus(), new Date(job.getSubmissionTime()));
        });

		System.out.println("");
		System.out.printf("[%d job%s]%n", count.get(), (count.get() == 1 ? "" : "s"));
	}
	
	/**
	 * <p>
	 * List the maps in the IMDG
	 * </p>
	 */
	@ShellMethod(key = "LIST", value = "List IMDG maps")
	public void list() {
		AtomicLong count = new AtomicLong();
		
		this.hazelcastInstance.getDistributedObjects().stream()
        .filter(distributedObject -> distributedObject instanceof IMap)
        .filter(distributedObject -> !distributedObject.getName().startsWith(Jet.INTERNAL_JET_OBJECTS_PREFIX))
        .map(distributedObject -> distributedObject.getName()).collect(Collectors.toCollection(TreeSet::new))
        .stream()
        .forEach(name -> {
        	count.incrementAndGet();
            IMap<?, ?> iMap = this.hazelcastInstance.getMap(name);
            
            System.out.println("");
            System.out.printf("IMap: '%s'%n", name);
            
            // Sort if possible
            Set<?> keys = iMap.keySet();
            if (!keys.isEmpty() && keys.iterator().next() instanceof Comparable) {
                keys = new TreeSet<>(keys);
            }

            keys.stream().forEach(key -> {
                System.out.printf("    -> '%s' -> %s%n", key, iMap.get(key));
            });

            System.out.printf("[%d entr%s]%n", iMap.size(), (iMap.size() == 1 ? "y" : "ies"));

        });

		System.out.println("");
		System.out.printf("[%d IMap%s]%n", count.get(), (count.get() == 1 ? "" : "s"));
	}

	/**
	 * <p>
	 * Create a processing pipeline, a chain of methods, and
	 * ask Hazelcast Jet to run it.
	 * </p>
	 */
	@ShellMethod(key = "START", value = "Start the Twitter pipeline")
	public String start() {
		if (this.twitterPipelineJob != null) {
			return String.format("Job already running, since %s%n", new Date(this.twitterPipelineJob.getSubmissionTime()));
		}
		
		Pipeline pipeline = this.twitterPipeline.build();
		
		JobConfig jobConfig = new JobConfig();
		jobConfig.setName(this.twitterPipeline.getClass().getSimpleName());
		
		this.twitterPipelineJob = this.jetInstance.newJob(pipeline, jobConfig);

		return String.format("Started job '%s' , id %d%n", this.twitterPipelineJob.getName(), this.twitterPipelineJob.getId());
	}

	/**
	 * <p>
	 * Stop all running Hazelcast Jet jobs. Probably just the one created
	 * by the {@link #start()} method above unless you add more.
	 * </p>
	 */
	@ShellMethod(key = "STOP", value = "Stop all running jobs")
	public String stop() {
		if (this.twitterPipelineJob == null) {
			return String.format("Job is not running%n");
		}

		String result = String.format("Stopping job '%s' , id %d%n", this.twitterPipelineJob.getName(), this.twitterPipelineJob.getId());
		
		this.twitterPipelineJob.cancel();
		
		this.twitterPipelineJob = null;
		
		return result;
	}

}
