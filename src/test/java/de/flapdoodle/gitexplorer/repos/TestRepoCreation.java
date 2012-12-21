package de.flapdoodle.gitexplorer.repos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import junit.framework.Assert;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.ObjectDatabase;
import org.eclipse.jgit.lib.RefDatabase;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.dfs.DfsRepositoryDescription;
import org.eclipse.jgit.storage.dfs.InMemoryRepository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.storage.file.ReflogReader;
import org.junit.Test;

import de.flapdoodle.gitexplorer.io.Files;

public class TestRepoCreation {

	@Test
	public void createRepo() throws NoFilepatternException, GitAPIException, IOException {
		File baseDir = Files.createTempDir("gitrepo");
		File gitDir = new File(baseDir, ".git");

		Repository repo = new FileRepositoryBuilder().setGitDir(gitDir).setWorkTree(baseDir).build();
		repo.create();

		Git git = new Git(repo);

		Files.write("Nothing", new File(baseDir,"my.sample"));
		
		git.add().addFilepattern("*.sample").call();
		git.commit().setMessage("first commit").call();
		
		Assert.assertTrue("clean up",Files.deleteAll(baseDir));
	}
	


}
