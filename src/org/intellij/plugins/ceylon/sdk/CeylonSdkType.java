package org.intellij.plugins.ceylon.sdk;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.Icon;

import org.intellij.plugins.ceylon.CeylonIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.intellij.ide.highlighter.JarArchiveFileType;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.SdkType;
import com.intellij.openapi.roots.types.BinariesOrderRootType;
import com.intellij.openapi.vfs.ArchiveFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

public class CeylonSdkType extends SdkType
{
	public CeylonSdkType()
	{
		super("Ceylon SDK");
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return CeylonIcons.Ceylon;
	}

	@Override
	public boolean isValidSdkHome(String path)
	{
		return new File(path, "bin/ceylon").exists();
	}

	@Override
	public String suggestSdkName(String currentSdkName, String sdkHome)
	{
		String version = getVersionString(sdkHome);
		return version == null ? "Ceylon SDK" : "Ceylon " + version;
	}

	@Nullable
	@Override
	public String getVersionString(String sdkHome)
	{
		File cpSh = new File(sdkHome, "bin/ceylon-cp.sh");

		if(cpSh.exists())
		{
			try
			{
				return Files.readLines(cpSh, Charset.defaultCharset(), new LineProcessor<String>()
				{
					private String version;

					@Override
					public boolean processLine(String s) throws IOException
					{
						if(s.startsWith("CEYLON_VERSION="))
						{
							version = s.substring(s.indexOf('=') + 1);
							return false;
						}
						return true;
					}

					@Override
					public String getResult()
					{
						return version == null ? null : version;
					}
				});
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	@NotNull
	@Override
	public String getPresentableName()
	{
		return "Ceylon SDK";
	}

	@Override
	public void setupSdkPaths(final Sdk sdk)
	{
		SdkModificator sdkModificator = sdk.getSdkModificator();

		VirtualFile homeDirectory = sdk.getHomeDirectory();

		if(homeDirectory != null)
		{
			VirtualFile libDir = homeDirectory.findChild("lib");

			if(libDir != null)
			{
				for(VirtualFile file : libDir.getChildren())
				{
					if("jar".equals(file.getExtension()))
					{
						VirtualFile jar = JarArchiveFileType.INSTANCE.getFileSystem().findFileByPath(file.getPath() + ArchiveFileSystem
								.ARCHIVE_SEPARATOR);
						sdkModificator.addRoot(jar, BinariesOrderRootType.getInstance());
					}
				}
			}

			addJarFromRepo(sdk, homeDirectory, sdkModificator, "ceylon.language", "car");
			addJarFromRepo(sdk, homeDirectory, sdkModificator, "com.redhat.ceylon.common", "jar");
			addJarFromRepo(sdk, homeDirectory, sdkModificator, "com.redhat.ceylon.compiler.java", "jar");
			addJarFromRepo(sdk, homeDirectory, sdkModificator, "com.redhat.ceylon.module-resolver", "jar");
			addJarFromRepo(sdk, homeDirectory, sdkModificator, "com.redhat.ceylon.typechecker", "jar");
		}

		sdkModificator.commitChanges();
	}

	private void addJarFromRepo(Sdk sdk, @NotNull VirtualFile homeDirectory, SdkModificator sdkModificator, String jarName, String extension)
	{
		VirtualFile file = homeDirectory.findFileByRelativePath(String.format("repo/%s/%s/%s-%s.%s", jarName.replace('.', '/'),
				sdk.getVersionString(), jarName, sdk.getVersionString(), extension));

		if(file != null)
		{
			VirtualFile jar = JarArchiveFileType.INSTANCE.getFileSystem().findFileByPath(file.getPath() + ArchiveFileSystem.ARCHIVE_SEPARATOR);
			sdkModificator.addRoot(jar, BinariesOrderRootType.getInstance());
		}
	}
}
